package com.example.mainservice.MRcontroller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class SparkController {

    @RequestMapping("/spark")
    public String submitSparkJob() {
        try {
            System.out.println("1");
            // 构建spark-submit命令
            String sparkSubmitCommand = "spark-submit --class WordCount --master spark://2.0.0.1:7077 D:\\ebook\\ebook_back\\se3353_25_spark_java\\se3353_25_spark_java\\target\\se3353_25_spark_java-1.0-SNAPSHOT.jar";

            // 执行命令
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", sparkSubmitCommand);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // 读取命令输出
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // 等待命令执行完成
            int exitCode = process.waitFor();
            System.out.println("1");
            // 读取 output 文件夹下的所有 .txt 文件内容并组装为一个字符串
            String result = readOutputFiles();

            return JSON.toJSONString("Spark job submitted. Exit code: " + exitCode + "\nResult:\n" + result);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return JSON.toJSONString("Error submitting Spark job: " + e.getMessage());
        }
    }

    private String readOutputFiles() {
        // 指定文件夹路径
        String filePath = "D:\\ebook\\ebook_back\\se3353_25_spark_java\\se3353_25_spark_java\\src\\main\\resources\\output\\part-00000";

        // 读取文件内容并返回
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file: " + filePath + "\n";
        }
    }
}
