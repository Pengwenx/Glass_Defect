package com.example.demo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class controller_dir {
    @CrossOrigin(origins="*")
    @GetMapping("/dir")
    public void dir(String[] args) {
// 指定文件夹路径
        String folderPath = "E:/items";

// 创建一个File对象表示文件夹
        File folder = new File(folderPath);

// 检查文件夹是否存在
        if (folder.exists() && folder.isDirectory()) {
// 调用递归方法来输出文件名
            listFiles(folder);
        } else {
            System.out.println("指定的路径不是一个有效的文件夹。");
        }
    }

    // 递归方法，用于输出文件夹下所有文件的名称
    private static void listFiles(File folder) {
// 获取文件夹下所有文件和子文件夹
        File[] files = folder.listFiles();

// 遍历文件和子文件夹
        if (files != null) {
            for (File file : files) {
// 如果是文件，输出文件名
                if (file.isFile()) {
                    System.out.println("文件: " + file.getName());
                }
// 如果是文件夹，递归调用方法
                else if (file.isDirectory()) {
                    System.out.println("文件夹: " + file.getName());
                    listFiles(file); // 递归调用
                }
            }
        }
    }
}
