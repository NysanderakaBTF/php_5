package com.example.php_5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan("com.example.php_5*")
@SpringBootApplication
public class Php5Application {

    public static void main(String[] args) {
        SpringApplication.run(Php5Application.class, args);
    }

}
