package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;


@RestController
public class controller_upload {
    @CrossOrigin(origins="*")
    @PostMapping("/upload")
    public String upload(MultipartFile photo)throws IOException {
        savefile(photo);
        return"successfully";
    }
    public void savefile(MultipartFile photo)throws IOException{
        File file=new File("E:/items/"+"test.jpg");
        photo.transferTo(file);
    }
}
