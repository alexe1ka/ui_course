package com.alexe1ka.view;

import com.alexe1ka.TestData;
import com.alexe1ka.model.BookImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LibraryGui {
    private JFrame frame;
    private JTabbedPane jTabbedPane;
    private JPanel jPanel;


    public void gui() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
//        frame.setResizable(false); //запрет на изменение размера фрейма

        //создаем две панели - одна для книжек,другая для читателей
        BookPanel bookPanel = new BookPanel();
        ReaderPanel readerPanel = new ReaderPanel();

        jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("Books", bookPanel);
        jTabbedPane.addTab("Readers", readerPanel);

        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jTabbedPane);

        //меню действий
        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Учет книг.Откройте таблицу с читателями и поработайте с ней");
            }
        });
//        JMenuItem addNewItem = new JMenuItem("Add new");
//        JMenuItem saveItem = new JMenuItem("Save all");
//        JMenuItem clearListItem = new JMenuItem("Clear all");
        fileMenu.add(helpItem);
//        fileMenu.add(addNewItem);
//        fileMenu.add(saveItem);
//        fileMenu.add(clearListItem);

        jMenuBar.add(fileMenu);
        frame.setJMenuBar(jMenuBar);
        frame.add(jPanel);
        frame.setVisible(true);
    }


}
