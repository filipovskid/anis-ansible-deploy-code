package com.filipovski.drboson.drboson.repository;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public interface FileStore {
    void save(String bucketName, String filename, InputStream fileStream, Optional<Map<String, String>> metadata);

    void delete(String bucketName, String filename);
}
