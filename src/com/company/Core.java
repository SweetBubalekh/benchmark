package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Core {

    public static File testFile = null;
    public static File dataDir = null;

    void Core() {
        Discs discs = new Discs();
        CreateForm();
        }
    void CreateForm(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TextComponentFrame frame = new TextComponentFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}
