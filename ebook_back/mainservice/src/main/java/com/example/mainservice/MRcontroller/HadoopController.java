package com.example.mainservice.MRcontroller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class HadoopController {
    @RequestMapping("/hadoop")
    public String submitHadoopJob() {
        try {
            System.out.println("1");
            // 构建spark-submit命令
            String sparkSubmitCommand = "hadoop jar D:\\ebook\\ebook_back\\se3353_24_hadoopmrsample\\se3353_24_hadoopmrsample\\target\\se3353_24_hadoopmrsample-1.0-SNAPSHOT.jar WordCount2  /input /output ";

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
            System.out.println("2");
            // 读取 output 文件夹下的所有 .txt 文件内容并组装为一个字符串
            String catCommand = "hadoop fs -cat /output/part-r-00000";

            // 执行命令
            ProcessBuilder processBuilder1 = new ProcessBuilder("cmd", "/c", catCommand);
            processBuilder1.redirectErrorStream(true);
            Process process1 = processBuilder1.start();

            // 读取命令输出
            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process1.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    output.append(line).append("\n");
                }
            }

            // 等待命令执行完成
            int exitCode1 = process1.waitFor();
            System.out.println("3");

            // 输出结果
            return JSON.toJSONString("Hadoop job submitted. Exit code: " + exitCode1 + "\nOutput:\n" + output.toString());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return JSON.toJSONString("Error submitting Spark job: " + e.getMessage());
        }
    }
}
