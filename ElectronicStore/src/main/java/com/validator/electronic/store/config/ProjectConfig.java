package com.validator.electronic.store.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig {

    @Bean
    public Cloudinary getCloudinary(){

        Map config = new HashMap<>();
        config.put("cloud_name","drzemli5v");
        config.put("api_key","618593146186923");
        config.put("api_secret","96Quox8GWSbVkQfFDmKe1lf3JfI");
        config.put("secure", true);

        return new Cloudinary(config);
    }

}
