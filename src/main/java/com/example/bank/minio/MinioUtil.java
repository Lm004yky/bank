package com.example.bank.minio;

import com.example.bank.config.MinioConfiguration;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.RemoveObjectArgs;
import io.minio.RemoveObjectsArgs;
import io.minio.Result;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class MinioUtil {

    private final MinioClient minioClient;

    private final MinioConfiguration minioConfiguration;

    @Autowired
    public MinioUtil(MinioClient minioClient, MinioConfiguration minioConfiguration) {
        this.minioClient = minioClient;
        this.minioConfiguration = minioConfiguration;
    }

    /** *  Check if the bucket exists  * * @param bucketName  Bucket name  * @return */
    @SneakyThrows
    public boolean bucketExists(String bucketName) {
        boolean found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        return found;
    }

    /** *  Create buckets  * * @param bucketName  Bucket name  */
    @SneakyThrows
    public boolean makeBucket(String bucketName) {
        boolean flag = bucketExists(bucketName);
        if (!flag) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build());

            return true;
        } else {
            return false;
        }
    }

    /** *  List all bucket names  * * @return */
    @SneakyThrows
    public List<String> listBucketNames() {
        List<Bucket> bucketList = listBuckets();
        List<String> bucketListName = new ArrayList<>();
        for (Bucket bucket : bucketList) {
            bucketListName.add(bucket.name());
        }
        return bucketListName;
    }

    /** *  List all buckets  * * @return */
    @SneakyThrows
    public List<Bucket> listBuckets() {
        return minioClient.listBuckets();
    }


    /** *  Delete buckets  * * @param bucketName  Bucket name  * @return */
    @SneakyThrows
    public boolean removeBucket(String bucketName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                //  There are object files , Delete failed
                if (item.size() > 0) {
                    return false;
                }
            }
            //  Delete buckets , Be careful , Only when the bucket is empty can it be deleted successfully .
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
            flag = bucketExists(bucketName);
            if (!flag) {
                return true;
            }
        }
        return false;
    }

    /** *  List all object names in the bucket  * * @param bucketName  Bucket name  * @return */
    @SneakyThrows
    public List<String> listObjectNames(String bucketName) {
        List<String> listObjectNames = new ArrayList<>();
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                listObjectNames.add(item.objectName());
            }
        }else{
            listObjectNames.add(" Bucket does not exist ");
        }
        return listObjectNames;
    }


    /** *  List all objects in the bucket  * * @param bucketName  Bucket name  * @return */
    @SneakyThrows
    public Iterable<Result<Item>> listObjects(String bucketName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            return minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(bucketName).build());
        }
        return null;
    }

    /** *  Upload files  * * @param bucketName * @param multipartFile */
    @SneakyThrows
    public void putObject(String bucketName, MultipartFile multipartFile, String filename, String fileType) {
        InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes());
        minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(filename).stream(
                                inputStream, -1, minioConfiguration.getFileSize())
                        .contentType(fileType)
                        .build());
    }


    /** *  File access path  * * @param bucketName  Bucket name  * @param objectName  The name of the object in the bucket  * @return */
    @SneakyThrows
    public String getObjectUrl(String bucketName, String objectName) {
        boolean flag = bucketExists(bucketName);
        String url = "";
        if (flag) {
            url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .expiry(2, TimeUnit.MINUTES)
                            .build());
            System.out.println(url);
        }
        return url;
    }


    /** *  Delete an object  * * @param bucketName  Bucket name  * @param objectName  The name of the object in the bucket  */
    @SneakyThrows
    public boolean removeObject(String bucketName, String objectName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
            return true;
        }
        return false;
    }

    /** *  Get a file object as a stream  * * @param bucketName  Bucket name  * @param objectName  The name of the object in the bucket  * @return */

    public InputStream getObject(String bucketName, String objectName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            StatObjectResponse statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.size() > 0) {
                InputStream stream =
                        null;
                try {
                    stream = minioClient.getObject(
                            GetObjectArgs.builder()
                                    .bucket(bucketName)
                                    .object(objectName)
                                    .build());
                } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                         InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                         XmlParserException e) {
                    e.printStackTrace();
                }
                return stream;
            }
        }
        return null;
    }

    /** *  Get the metadata of the object  * * @param bucketName  Bucket name  * @param objectName  The name of the object in the bucket  * @return */

    public StatObjectResponse statObject(String bucketName, String objectName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            StatObjectResponse stat = null;
            try {
                stat = minioClient.statObject(
                        StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
            } catch (Exception e){
                return null;
            }
            return stat;
        }
        return null;
    }

    /** *  Delete multiple file objects of the specified bucket , Returns the list of objects with deletion errors , Delete all successfully , Return to empty list  * * @param bucketName  Bucket name  * @param objectNames  Contains multiple... To delete object Iterator object with name  * @return */
    @SneakyThrows
    public boolean removeObject(String bucketName, List<String> objectNames) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            List<DeleteObject> objects = new LinkedList<>();
            for (String objectName : objectNames) {
                objects.add(new DeleteObject(objectName));
            }
            Iterable<Result<DeleteError>> results =
                    minioClient.removeObjects(
                            RemoveObjectsArgs.builder().bucket(bucketName).objects(objects).build());
            for (Result<DeleteError> result : results) {
                DeleteError error = result.get();
                System.out.println(
                        "Error in deleting object " + error.objectName() + "; " + error.message());
                return false;
            }
        }
        return true;
    }

    /** *  Get a file object as a stream （ Breakpoint download ） * * @param bucketName  Bucket name  * @param objectName  The name of the object in the bucket  * @param offset  The position of the start byte  * @param length  Length to read  ( Optional , If there is no value, it means reading to the end of the file ) * @return */
    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName, long offset, Long length) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            StatObjectResponse statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.size() > 0) {
                return minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(bucketName)
                                .object(objectName)
                                .offset(offset)
                                .length(length)
                                .build());
            }
        }
        return null;
    }


    /** *  adopt InputStream Upload object  * * @param bucketName  Bucket name  * @param objectName  The name of the object in the bucket  * @param inputStream  Stream to upload  * @param contentType  Type of file to upload  MimeTypeUtils.IMAGE_JPEG_VALUE * @return */
    @SneakyThrows
    public boolean putObject(String bucketName, String objectName, InputStream inputStream,String contentType) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                                    inputStream, -1, minioConfiguration.getFileSize())
                            .contentType(contentType)
                            .build());
            StatObjectResponse statObject = statObject(bucketName, objectName);
            return statObject != null && statObject.size() > 0;
        }
        return false;
    }

}
