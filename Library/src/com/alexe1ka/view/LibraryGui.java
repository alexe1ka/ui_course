package com.alexe1ka.view;

import com.alexe1ka.TestData;
import com.alexe1ka.model.Book;
import com.alexe1ka.model.MyTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LibraryGui {
    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private JScrollPane scroller;


    public void gui() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setResizable(false);


        TestData testData = new TestData();
        Set<Book> books = testData.readFromFile();
        List<Book> listBooks = new ArrayList<>();
        listBooks.addAll(books);
        MyTableModel tableModel = new MyTableModel(listBooks);


        table = new JTable(tableModel);
        Font font = new Font("TimesNewRoman", Font.BOLD, 14);
        table.setFont(font);

        scroller = new JScrollPane(table);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        panel = new JPanel();
        panel.add(scroller);

        frame.getContentPane().add(BorderLayout.CENTER, panel);


        frame.setVisible(true);

    }


}
