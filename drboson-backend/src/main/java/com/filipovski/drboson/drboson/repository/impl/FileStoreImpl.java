package com.filipovski.drboson.drboson.repository.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.filipovski.drboson.drboson.repository.FileStore;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Repository
public class FileStoreImpl implements FileStore {

    private final AmazonS3 s3Client;

    public FileStoreImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public void save(String bucketName, String filename, InputStream fileStream, Optional<Map<String, String>> metadata) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            metadata.ifPresent(map -> map.forEach(objectMetadata::addUserMetadata));

            PutObjectRequest request = new PutObjectRequest(bucketName, filename, fileStream, objectMetadata);
            s3Client.putObject(request);
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String bucketName, String filename) {
        try {
            DeleteObjectRequest request = new DeleteObjectRequest(bucketName, filename);
            s3Client.deleteObject(request);
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
    }
}
