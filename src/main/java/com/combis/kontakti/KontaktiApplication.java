package com.combis.kontakti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class KontaktiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KontaktiApplication.class, args);
    }

}
