package com.example.demoCloth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * Runs the Rest API.
 * This Rest API queries the database by data, product identifier and brand identifier.
 * Here Spring boot configuration is defined.
 */
@SpringBootApplication(proxyBeanMethods = false)
@RestController
public class DemoClothApplication {

    /**
     * Runs the Rest API.
     *
     * @param args Parameters passed to run the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoClothApplication.class, args);
    }

}
