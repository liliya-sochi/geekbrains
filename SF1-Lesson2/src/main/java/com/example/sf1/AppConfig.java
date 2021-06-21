package com.example.sf1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.sf1")
public class AppConfig {

    @Autowired
    private ProductService productService;
}
