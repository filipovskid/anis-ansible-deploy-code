package com.filipovski.drboson.drboson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class DrbosonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrbosonApplication.class, args);
    }

}
