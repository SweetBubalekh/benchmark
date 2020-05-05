package com.company;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static com.company.Core.testFile;
import static com.company.Core.dataDir;

public class Discs {

    void StartCheck(JTextArea T) {
        testFile = new File(dataDir.getAbsolutePath()+File.separator+"data.tmp");
        String mode = "rwd";
        try(RandomAccessFile AccFile = new RandomAccessFile(testFile, mode)) {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
