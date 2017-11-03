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
import java.util.TreeSet;

public class BookPanel extends JPanel {
    private JTable bookTable;
    private JScrollPane bookScroller;
    private Button newBookButton;
    private Button saveBookList;


    public BookPanel() {
        TestData testData = TestData.getInstance();
        List<BookImpl> listBooks = new ArrayList<>();
        //listBooks.addAll(testData.readBookFromFile());// добавление тестовых данных
        MyTableModel bookTableModel = new MyTableModel(listBooks);

        //вкладка с книгами отображает таблицу со всеми книгами.формирование таблицы
        //формирование таблицы с помощью класса MyTableModel
        bookTable = new JTable(bookTableModel);
        bookTable.setRowHeight(30);
        bookTable.getColumnModel().getColumn(0).setPreferredWidth(300);
        bookTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        bookTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        bookTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        bookTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

        Font font = new Font("TimesNewRoman", Font.BOLD, 12);
        bookTable.setFont(font);
        bookScroller = new JScrollPane(bookTable);
        bookScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        bookScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //выбор жанра из существующих
        JComboBox comboBox = new JComboBox();
        Genre[] listOfEnumValue = Genre.values();
        for (int i = 0; i < listOfEnumValue.length; i++) {
            comboBox.addItem(listOfEnumValue[i]);
        }
        bookTable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));


        newBookButton = new Button("New book");
        newBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookTableModel.addNewBook(new BookImpl(null, null, null, null, null, false));//создаю пустую книгу
                bookTable.updateUI();
            }
        });


        saveBookList = new Button("Save new list");
        saveBookList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<BookImpl> set = new TreeSet<>();
                set.addAll(bookTableModel.getBooksList());
                testData.saveToFile(set, "books");
            }
        });

        //вкладки
//       setLayout(new GridLayout());
        setLayout(new BorderLayout());
        add(bookScroller);
        JPanel buttonBookPanel = new JPanel();
        buttonBookPanel.add(BorderLayout.SOUTH, newBookButton);
        buttonBookPanel.add(BorderLayout.SOUTH, saveBookList);
        add(BorderLayout.SOUTH, buttonBookPanel);
    }
}
