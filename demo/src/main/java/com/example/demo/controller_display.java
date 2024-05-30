package com.example.demo;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/images")
public class controller_display {

    private static final String UPLOADS_DIR = "E:/items/";
    @CrossOrigin(origins="*")
    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage() throws IOException {
        Path imagePath = Path.of(UPLOADS_DIR, "annotated_image.jpg");
        Resource resource = new FileSystemResource(imagePath.toString());

        if (resource.exists() && Files.isRegularFile(imagePath)) {
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return ResponseEntity.ok().body(imageBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


