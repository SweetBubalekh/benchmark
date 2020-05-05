package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Core {

    public static File testFile = null;
    public static File dataDir = null;

    void Core(){
        Discs discs = new Discs();
        CreateForm();
        //discs.DiscViev();
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
