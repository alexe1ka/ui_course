package com.alexe1ka.view;

import com.alexe1ka.TestData;
import com.alexe1ka.model.BookImpl;
import com.alexe1ka.model.Genre;
import com.alexe1ka.model.MyTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LibraryGui {
    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private JScrollPane scroller;
    private Button editButton;


    public void gui() {
        TestData testData = new TestData();
        Set<BookImpl> books = testData.readFromFile();
        List<BookImpl> listBooks = new ArrayList<>();
        listBooks.addAll(books);
        MyTableModel tableModel = new MyTableModel(listBooks);


        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setResizable(false);


        table = new JTable(tableModel);
        Font font = new Font("TimesNewRoman", Font.BOLD, 14);
        table.setFont(font);

        scroller = new JScrollPane(table);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JComboBox comboBox = new JComboBox();
        Genre[] listOfEnumValue = Genre.values();
        for (int i = 0;i<listOfEnumValue.length;i++){
            comboBox.addItem(listOfEnumValue[i]);
        }
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));



        editButton = new Button("Edit table");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        panel = new JPanel();
        panel.add(scroller);


        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem addNewItem = new JMenuItem("Add new");
        JMenuItem clearListItem = new JMenuItem("Clear all");
        fileMenu.add(addNewItem);
        fileMenu.add(clearListItem);

        jMenuBar.add(fileMenu);
        frame.setJMenuBar(jMenuBar);
        frame.getContentPane().add(BorderLayout.CENTER, panel);


        frame.setVisible(true);

    }


}
