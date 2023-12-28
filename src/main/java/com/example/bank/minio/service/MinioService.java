package com.example.bank.minio.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface MinioService {
    Boolean saveObject(String bucketName, MultipartFile object, String objectName);
    Resource getObject(String bucketName, String fileName);

    Boolean deleteObject(String bucketName, String objectName);
}
