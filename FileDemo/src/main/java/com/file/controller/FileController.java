package com.file.controller;

import com.file.playload.FileResponse;
import com.file.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(@RequestParam("image")MultipartFile image) {
        String fileName = null;
        try {
            fileName = this.fileService.uploadImage(path, image);

            //repository call
            //file name save


        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null, "image is not upload due to error on server!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new FileResponse(fileName, "image is successfully uploaded!"), HttpStatus.OK);
    }

    //method to serve files
    @GetMapping(value = "/profiles/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadFiles(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

    //localhost:8080/images/abc.png
    //http://localhost:8080/file/profiles/ac02bd2d-bc7e-4618-b021-ded6e6d6b3cc.jpg
    //http://localhost:8080/file/profiles/23ad2a15-ae1c-4978-8b80-39fd6434e454.png
}
