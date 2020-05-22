package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Signature;
import java.util.Properties;

public class TextComponentFrame extends JFrame {
    Discs discs = new Discs();
    static final int DEFAULT_WIDTH = 640;
    static final int DEFAULT_HEIGHT = 480;
    JPanel Panel1 = new JPanel();
    final JTextArea textArea = new JTextArea(8,55);
    File[] roots = File.listRoots();
    //кнопки
    JButton Jbutton = new JButton("Отобразить читаемые диски.");
    JButton JbuttonUp = new JButton("↑");
    JButton JbuttonDown = new JButton("↓");
    JButton JbuttonSelect = new JButton("Выбрать диск для проверки.");
    JButton JbuttonStart = new JButton("Начать проверку.");
    final double gb = 1024*1024*1024;
    JLabel Label1 = new JLabel("Информация о системе:");
    int s=0;//счетчик положения стрелки выбора дисков

    public TextComponentFrame() {
        setTitle("FlashBenchmark");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        textArea.setLineWrap(true);
        Panel1.add(Label1);
        Panel1.add(textArea);//панелька со списком дисков
        Panel1.add(Jbutton);//кнопка проверки свободного пространства
        Panel1.add(JbuttonUp);
        Panel1.add(JbuttonDown);
        Panel1.add(JbuttonSelect);
        Panel1.add(JbuttonStart);

        JbuttonDown.hide();
        JbuttonUp.hide();
        JbuttonSelect.hide();
        JbuttonStart.hide();
        textArea.setEditable(false);
        textArea.setText("Сведения о системе:\nИнформация о процессоре: "+ System.getenv("PROCESSOR_IDENTIFIER")+ "\nОперационная система: " );
        JbuttonUp.addActionListener(new ActionListener() {//перемещение вверх при выборе диска
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                s--;
                Show();
            }
        });//Кнопка вверх
        JbuttonDown.addActionListener(new ActionListener() {//перемещение вниз при выборе диска
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                s++;
                Show();
            }
        });//Кнопка вниз
        Jbutton.addActionListener(new ActionListener() {//главная кнопка
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                Show();
                JbuttonDown.show();
                JbuttonUp.show();
                JbuttonSelect.show();
                JbuttonStart.hide();
                Jbutton.setText("Обновить список дисков.");
            }
        });//Кнопка отображения\обновления дисков
        JbuttonSelect.addActionListener(new ActionListener() {//кнопка выбора диска для проверки
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               DiscCheck();
            }
        });//Кнопка выбора диска

        JbuttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String a=" ";
                a += discs.StartCheck(roots[s].getAbsolutePath(), textArea);
                JOptionPane.showMessageDialog(Panel1, "Укажите место для сохранения результатов проверки.");

                // Определение режима - только каталог

                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.TXT","*.*");
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(roots[s]);
                fc.setFileFilter(filter);
                if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
                    try ( FileWriter fw = new FileWriter(fc.getSelectedFile()+".txt") ) {
                        fw.write(a);
                    }
                    catch (IOException e ) {
                        System.out.println("Всё погибло!");
                    }
                }
            }
        });//Кнопка начала проверки

        add(Panel1);
    }

    void Show(){
        roots = File.listRoots();
        Label1.setText("Список носителей, доступных для проверки: ");
        textArea.setText("");
        if (s > roots.length-1) s-=1;
        if (s < 0) s = 0;
        for(int i = 0; i < roots.length; i++) {
            if (roots[i].canWrite()) {
                if (s == i) textArea.append("------------------------------------------------------------\n->");
                textArea.append("Диск #" + (i + 1) + " " + roots[i].getAbsolutePath() + ". Свободное место на диске: " + (int)(roots[i].getFreeSpace() / (gb)) + " Гб.\n");
                if (s == i) textArea.append("------------------------------------------------------------\n");
            }
        }
    }
    void DiscCheck(){
        Jbutton.setText("Отобразить список дисков.");
        if (roots[s].canWrite()&& roots[s].getFreeSpace()/(gb/1024)>10) {
            textArea.setText("Проверка диска возможна. Путь к диску: "+ roots[s].getAbsolutePath() + ". Количество свободного пространства на диске: "+ (int)(roots[s].getFreeSpace() / (gb)) + " Гб.\n");
            JbuttonDown.hide();
            JbuttonUp.hide();
            JbuttonSelect.hide();
            JbuttonStart.show();
        }
        else {
            textArea.setText("Проверка диска невозможна, недостаточно свободного места.");
            JbuttonDown.hide();
            JbuttonUp.hide();
            JbuttonSelect.hide();
        }
    }
}
