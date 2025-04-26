package com.file.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
public class FileController {

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/uploadFiles")
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("images") MultipartFile[] files){

        this.logger.info("{} files uploaded successfully",files.length);
        Arrays.stream(files).forEach(multipartFile ->{
            logger.info("file name : {}",multipartFile.getOriginalFilename());
            logger.info("file type : {}",multipartFile.getContentType());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        });

        /*for(MultipartFile multipartFile : files){
            logger.info("file name : {}",multipartFile.getOriginalFilename());
            logger.info("file type : {}",multipartFile.getContentType());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        }*/

        return ResponseEntity.ok("Files Uploaded");
    }

}
