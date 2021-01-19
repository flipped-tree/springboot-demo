package com.example.springboot.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 读文件任务
 *
 * @author xingce
 * @date 2021/01/07 10:16
 */
public class FileReadThread implements Runnable {

    private String fileName;
    private CountDownLatch countDownLatch;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        List<FileContent> result = getData();
        LinkedBlockingQueue<FileContent> queue = DataHolder.getQueue();
        result.forEach(queue::offer);
        countDownLatch.countDown();
    }

    public List<FileContent> getData() {
        List<FileContent> resultList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] array = line.split(",");
                    FileContent fileContent = new FileContent();
                    fileContent.setId(array[0]);
                    fileContent.setGroupId(array[1]);
                    fileContent.setQuota(Float.parseFloat(array[2]));
                    resultList.add(fileContent);
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
        }
        return resultList;
    }
}
