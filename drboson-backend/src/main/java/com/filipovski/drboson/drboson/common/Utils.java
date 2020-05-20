package com.filipovski.drboson.drboson.common;

import com.amazonaws.services.s3.model.S3Object;
import com.filipovski.drboson.drboson.config.AmazonS3Config;
import com.filipovski.drboson.drboson.repository.FileStore;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.InputStream;

public class Utils {

    public static StreamingResponseBody getStreamingResponseBody(FileStore fileStore, String bucketName,
                                                                 String location) throws Exception {
        S3Object object = fileStore.download(bucketName, location)
                .orElseThrow(Exception::new);

        InputStream inputStream = object.getObjectContent().getDelegateStream();

        StreamingResponseBody responseBody = outputStream -> {

            int numberOfBytesToWrite;
            byte[] data = new byte[1024];
            while ((numberOfBytesToWrite = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, numberOfBytesToWrite);
            }

            inputStream.close();
            object.close();
        };

        return responseBody;
    }
}
