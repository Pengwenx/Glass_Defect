//package com.example.demo;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//
//import java.io.File;
//import java.io.IOException;
//
//
//@RestController
//public class controller_upload {
//    @CrossOrigin(origins="*")
//    @PostMapping("/upload")
//    public String upload(MultipartFile photo)throws IOException {
//        savefile(photo);
//        return"successfully";
//    }
//    public void savefile(MultipartFile photo)throws IOException{
//        File file=new File("E:/items/"+"test.jpg");
//        photo.transferTo(file);
//    }
//}

//回归
package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class controller_upload {

    private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif");

    @CrossOrigin(origins="*")
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("photo") MultipartFile photo) {
        if (photo == null || photo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file provided or file is empty");
        }

        String mimeType = photo.getContentType();
        if (mimeType == null || !ALLOWED_MIME_TYPES.contains(mimeType)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type. Only image files are allowed.");
        }

        try {
            savefile(photo);
            return ResponseEntity.ok("Successfully uploaded");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving file: " + e.getMessage());
        }
    }

    public void savefile(MultipartFile photo) throws IOException {
        File file = new File("E:/items/" + "test.jpg");
        photo.transferTo(file);
    }
}

