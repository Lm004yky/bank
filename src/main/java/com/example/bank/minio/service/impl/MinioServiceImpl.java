package com.example.bank.minio.service.impl;

import com.example.bank.minio.MinioUtil;
import com.example.bank.minio.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class MinioServiceImpl implements MinioService {

    private MinioUtil minioUtil;

    @Autowired
    public MinioServiceImpl(MinioUtil minioUtil) {
        this.minioUtil = minioUtil;
    }

    @Override
    public Boolean saveObject(String bucketName, MultipartFile object, String objectName) {
        try {
            return minioUtil.putObject(bucketName,objectName,object.getInputStream(),object.getContentType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Resource getObject(String bucketName, String fileName) {
        Resource file =  new InputStreamResource(minioUtil.getObject(bucketName,fileName));
        return file;
    }

    @Override
    public Boolean deleteObject(String bucketName, String objectName) {
        return minioUtil.removeObject(bucketName, objectName);
    }

}
