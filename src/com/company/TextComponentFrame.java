package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.Signature;
import java.util.Properties;

public class TextComponentFrame extends JFrame {
    Discs discs = new Discs();
    static final int DEFAULT_WIDTH = 640;
    static final int DEFAULT_HEIGHT = 480;
    JPanel Panel1 = new JPanel();
    final JTextArea textArea = new JTextArea(8,50);
    JButton Jbutton = new JButton("Отобразить читаемые диски.");
    JButton JbuttonUp = new JButton("↑");
    JButton JbuttonDown = new JButton("↓");
    JLabel Label1 = new JLabel("Информация о системе:");
    int s=0;

    public TextComponentFrame() {
        setTitle("FlashBenchmark");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        textArea.setLineWrap(true);
        Panel1.add(Label1);
        Panel1.add(textArea);//панелька с текстом дисков
        Panel1.add(Jbutton);//кнопка проверки свободного пространства
        textArea.setText("Сведения о системе:\nИнформация о процессоре: "+ System.getenv("PROCESSOR_IDENTIFIER")+ "\nОперационная система: " );
        JbuttonUp.addActionListener(new ActionListener() {//перемещение вверх при выборе диска
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                s--;
                Show();
            }
        });
        JbuttonDown.addActionListener(new ActionListener() {//перемещение вниз при выборе диска
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                s++;
                Show();
            }
        });
        Jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Show();
                Panel1.add(JbuttonUp);
                Panel1.add(JbuttonDown);
            }
        });

        add(Panel1);
    }

    void Show(){
        Label1.setText("Список носителей, доступных для проверки: ");
        textArea.setText("");
        File[] roots = File.listRoots();
        if (s > roots.length-1) s-=1;
        if (s < 0) s = 0;
        for(int i = 0; i < roots.length; i++) {
            if (roots[i].canWrite()) {
                if (s == i) textArea.append("------------------------------------------------------------\n->");
                textArea.append("Диск #" + (i + 1) + " " + roots[i].getAbsolutePath() + ". Свободное место на диске: " + roots[i].getFreeSpace() / (1024 * 1024 * 1024) + " Гб.\n");
                if (s == i) textArea.append("------------------------------------------------------------\n");
            }

        }
    }
}
