package com.todo_manager.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class fileControlller {

    Logger logger= LoggerFactory.getLogger("fileControlller.class");

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("image") MultipartFile file){

       logger.info("original file name: {}", file.getOriginalFilename());
        logger.info("File name: {}", file.getName());
        logger.info("File size: {}", file.getSize());
        logger.info("File type: {}", file.getContentType());


        return "File uploaded successfully";

    }

    @PostMapping("/multiple")
    public String multiFileUpload(@RequestParam("file") MultipartFile[] files){
        Arrays.stream(files).forEach(file -> {
            System.out.println("----------------------------------------");
            logger.info("File name: {}", file.getOriginalFilename());
            logger.info("File size: {}", file.getSize());
            logger.info("File type: {}", file.getContentType());
            System.out.println("----------------------------------------");

        });

        return "All files uploaded sucessfully";
    }

    @GetMapping("/image")
    public void getImages(HttpServletResponse response) {
        try{
            InputStream input=new FileInputStream("D:images/new.jpg");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(input, response.getOutputStream());
        } catch (FileNotFoundException e) {
            logger.error("File not found: {}", e.getMessage());
        } catch (IOException e) {
            logger.error("Error reading file: {}", e.getMessage());
        }
    }
}
