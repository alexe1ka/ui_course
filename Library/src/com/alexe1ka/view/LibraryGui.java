package com.alexe1ka.view;

import com.alexe1ka.TestData;
import com.alexe1ka.model.BookImpl;
import com.alexe1ka.model.BookReader;
import com.alexe1ka.model.Genre;
import com.alexe1ka.model.MyTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

        //TODO ВЫТАЩИТЬ ДВЕ ПАНЕЛИ
        jTabbedPane.addTab("Readers", readerPanel);
        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jTabbedPane);


        //меню действий
        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openFileItem = new JMenuItem("Open file");
        openFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showDialog(null, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
//                    listBooks.addAll(testData.readBookFromFile(file.getAbsolutePath()));
//                    bookTable.updateUI();
                }
            }
        });
        JMenuItem addNewItem = new JMenuItem("Add new");
        JMenuItem saveItem = new JMenuItem("Save all");
        JMenuItem clearListItem = new JMenuItem("Clear all");
        fileMenu.add(openFileItem);
        fileMenu.add(addNewItem);
        fileMenu.add(saveItem);
        fileMenu.add(clearListItem);

        jMenuBar.add(fileMenu);
        frame.setJMenuBar(jMenuBar);
        frame.getContentPane().add(jPanel);
        frame.setVisible(true);
    }
}
