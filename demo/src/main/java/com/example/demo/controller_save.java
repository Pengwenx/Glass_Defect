package com.example.demo;

import com.example.demo.entity.Photo;
import com.example.demo.list_to_json;
import com.example.demo.mapper.Photomapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
public class controller_save {

    @Autowired
    private Photomapper photomapper;
    @CrossOrigin(origins="*")
    @GetMapping("/save")
    public String test(){
        try {
            // 源文件路径
            Path sourcePath = Paths.get("E:/items/"+"test.jpg");
            Path sourcePath_result = Paths.get("E:/items/"+"annotated_image.jpg");
            // 目标文件夹路径
            Path destinationFolder = Paths.get("E:/items/data/");
            long timestamp = System.currentTimeMillis();
            // 构建新的文件名，附加时间戳
            String newFileName = "upload_" + timestamp + ".jpg";
            Path destinationPath = destinationFolder.resolve(newFileName);

            String newFileName_result = "result_" + timestamp + ".jpg";
            Path destinationPath_result = destinationFolder.resolve(newFileName_result);
            // 复制文件
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(sourcePath_result, destinationPath_result, StandardCopyOption.REPLACE_EXISTING);

            Photo save_photo=new Photo(newFileName,newFileName_result);
            photomapper.save(save_photo);
//            System.out.println("存储完毕");
            return "savesuccessfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "error" + e.getMessage();
        }
    }
}
