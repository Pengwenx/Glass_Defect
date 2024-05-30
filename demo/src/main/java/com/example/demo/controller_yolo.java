package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class controller_yolo {
    @CrossOrigin(origins="*")
    @GetMapping("/yolo")
    public String get() throws IOException, InterruptedException {
        try {
            // 路径参数
            String pythonScript = "E:\\yolov8\\ultralytics-main\\defect.py";
            String condaPath = "D:\\File\\Anaconda\\Scripts\\conda.exe";
            String envName = "yolov8";
            String workingDirectory = "E:\\yolov8\\ultralytics-main"; // 设置工作路径

            // 构建命令
            String[] command = {condaPath, "run", "-n", envName, "python", pythonScript};
//            String[] command = {"python", pythonScript};
            // 创建进程构建器
            ProcessBuilder pb = new ProcessBuilder(command);

            // 设置工作路径
            pb.directory(new File(workingDirectory));

            // 启动进程执行命令
            Process process = pb.start();

            // 从进程的输出流中读取输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待命令执行完成
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("内爆识别任务执行成功！");
            } else {
                System.out.println("内爆识别任务执行失败！");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "runsuccessfully";
    }
}
