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
    private JTable bookTable;
    private JScrollPane scrollerBook;
    private Button editButton;
    private JTabbedPane jTabbedPane;


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


        bookTable = new JTable(tableModel);
        Font font = new Font("TimesNewRoman", Font.BOLD, 14);
        bookTable.setFont(font);

        scrollerBook = new JScrollPane(bookTable);
        scrollerBook.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollerBook.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JComboBox comboBox = new JComboBox();
        Genre[] listOfEnumValue = Genre.values();
        for (int i = 0; i < listOfEnumValue.length; i++) {
            comboBox.addItem(listOfEnumValue[i]);
        }
        bookTable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));


        editButton = new Button("Edit bookTable");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("Books", scrollerBook);
        jTabbedPane.addTab("Readers", new JPanel());

        panel = new JPanel();
        panel.add(jTabbedPane);


        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem addNewItem = new JMenuItem("Add new");
        JMenuItem saveItem = new JMenuItem("Save all");
        JMenuItem clearListItem = new JMenuItem("Clear all");
        fileMenu.add(addNewItem);
        fileMenu.add(saveItem);
        fileMenu.add(clearListItem);

        jMenuBar.add(fileMenu);
        frame.setJMenuBar(jMenuBar);
        frame.getContentPane().add(BorderLayout.CENTER, panel);


        frame.setVisible(true);

    }


}
