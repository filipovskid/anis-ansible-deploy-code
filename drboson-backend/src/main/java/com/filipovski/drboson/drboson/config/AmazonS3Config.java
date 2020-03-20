package com.filipovski.drboson.drboson.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Data
@Configuration
@PropertySource("classpath:security/aws.yml")
public class AmazonS3Config {
    @Value("${amazonProperties.awsAccessKeyId}")
    private String accessKeyId;

    @Value("${amazonProperties.awsSecretKey}")
    private String secretKey;

    @Value("${amazonProperties.datasetBucketName}")
    private String datasetBucketName;

    @Value("${amazonProperties.imagesBucketName}")
    private String imagesBucketName;

    @Bean
    @PostConstruct
    public AmazonS3 amazonS3() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(this.accessKeyId, this.secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        return s3Client;
    }
}
