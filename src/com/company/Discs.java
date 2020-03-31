package com.company;

import java.io.File;

public class Discs {
    void DiscViev() {
        System.out.println("Список дисков:");
        File[] roots = File.listRoots();
        for(int i = 0; i < roots.length; i++) {
            if(roots[i].canWrite())
                System.out.println("Диск #" + (i+1) + " " + roots[i].getAbsolutePath() + ". Свободное место на диске: " + roots[i].getFreeSpace()/(1024*1024*1024) + " Гб.");
        }
    }

    
}
