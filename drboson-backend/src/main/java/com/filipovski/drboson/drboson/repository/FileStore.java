package com.filipovski.drboson.drboson.repository;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public interface FileStore {
    void save(String bucketName, String filename, InputStream fileStream, Optional<Map<String, String>> metadata);

    Optional<S3Object> download(String bucketName, String fileKey) throws IOException;

    void delete(String bucketName, String filename);
}
