package com.example.demo;
import com.example.demo.entity.Photo;
import com.example.demo.list_to_json;
import com.example.demo.mapper.Photomapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class controller_display5 {

    // Assuming you have a service to interact with the database

    @Autowired
    private Photomapper photomapper;

    private static final String UPLOADS_DIR = "E:/items/data/";
    @CrossOrigin(origins="*")
    @GetMapping(value = "/display5",produces = MediaType.IMAGE_JPEG_VALUE)
    public String getImages() throws IOException {
        List<Photo> imageEntities = photomapper.find(); // Assuming a method to get five images from the database
        List<byte[]> imageBytesList = new ArrayList<>();
        int sum=0;
        for (Photo Photo : imageEntities) {
            Path imagePath = Path.of(UPLOADS_DIR, Photo.getResultphoto());
            System.out.println(Photo.getResultphoto());
            if (Files.isRegularFile(imagePath)) {
                byte[] imageBytes = Files.readAllBytes(imagePath);
                imageBytesList.add(imageBytes);
                sum++;
            }
            if(sum==5){break;}
        }
        list_to_json listToJson=new list_to_json();
        String result;
        result=listToJson.convertListToJson(imageBytesList);
        System.out.println(result);
        if (!imageBytesList.isEmpty()) {
            System.out.println("yes");
            return result;
        } else {
            System.out.println("no");
            return "返回失败";
        }
    }
}
