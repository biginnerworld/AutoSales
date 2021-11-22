package com.karpovskiy.autosales;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class AutoSalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoSalesApplication.class, args);
    }

}
