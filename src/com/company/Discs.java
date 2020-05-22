package com.company;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;


public class Discs {
    String data = "ЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯЯ";
    String StartCheck(String path, JTextArea textArea) {
        textArea.setText("Подождите, идет создание файла для записи.\n");
        try {
            File report = File.createTempFile("reportFile", ".txt");
            System.out.println(report.getCanonicalPath());
            File tempFile = File.createTempFile("data", null);
            //System.out.println(tempFile.getCanonicalPath());
            float time = TimeUnit.SECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS);

            while (tempFile.length() / (1024 * 1024) < 10.0) {
                Files.write(Paths.get(tempFile.getCanonicalPath()), data.getBytes(), StandardOpenOption.APPEND);
            }

            time = (TimeUnit.SECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS) - time);
            textArea.append("Файл успешно создан за " + time + " секунд ,идет запись и проверка времени записи.\n");
            String a = "Файл успешно создан за " + time + " секунд ,идет запись и проверка времени записи.\n" + System.lineSeparator();
            a += (WriteData(tempFile.getCanonicalPath(), path + File.separator + "tmp" + File.separator + "data.tmp", path, textArea)) + System.lineSeparator();
            //Files.write(Paths.get(report.getCanonicalPath()), a.getBytes(), StandardOpenOption.APPEND);
            tempFile.deleteOnExit();
            return a;
            //report.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

        String WriteData(String pathSource, String pathDestination, String folderDest, JTextArea textArea){
            String b = new String();
            File folder = new File(folderDest + File.separator + "tmp") ;
            folder.mkdir();
            File source = new File (pathSource);
            File destination = new File(pathDestination);
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    float time = (TimeUnit.SECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS));
                    FileUtils.copyFile(source, destination);
                    time = (TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS) - time)/1000;
                    textArea.append("\nВремя записи попытки №" + (i+1)+ " равняется " + time + " милисекунд");
                    b +=("\nВремя записи попытки №" + (i+1)+ " равняется " + time + " милисекунд")+ System.lineSeparator();
                    destination.delete();
                }
                destination.deleteOnExit();
            } catch (IOException | InterruptedException e){
                e.printStackTrace();
            }
            return b;
        }

}
