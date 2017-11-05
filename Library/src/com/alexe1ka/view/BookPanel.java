package com.alexe1ka.view;

import com.alexe1ka.TestData;
import com.alexe1ka.model.BookImpl;
import com.alexe1ka.model.Genre;
import com.alexe1ka.model.MyTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BookPanel extends JPanel implements ActionListener {
    private JTable bookTable;
    private JScrollPane bookScroller;

    private Button openDataButton;
    private Button newBookButton;
    private Button saveBookList;

    private List<BookImpl> listBooks;
    private MyTableModel bookTableModel;


    public BookPanel() {
        listBooks = new ArrayList<>();
        //listBooks.addAll(testData.readBookFromFile());// добавление тестовых данных
        bookTableModel = new MyTableModel(listBooks);

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
        JComboBox<Genre> comboBox = new JComboBox<>();
        Genre[] listOfEnumValue = Genre.values();
        for (Genre genre : listOfEnumValue) {
            comboBox.addItem(genre);
        }
        bookTable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));


        //кнопки
        openDataButton = new Button("Open data");
        openDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showDialog(null, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    listBooks.addAll(TestData.getInstance().readBookFromFile(file.getAbsolutePath()));
                    bookTable.updateUI();
                }
            }
        });


        AddBookDialog addBookDialog = new AddBookDialog(null, false);

        newBookButton = new Button("New book");
        newBookButton.addActionListener(this);


        saveBookList = new Button("Save new list");
        saveBookList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save books DB");
                int userSelection = fileChooser.showSaveDialog(null);
                Set<BookImpl> set = new TreeSet<>();
                set.addAll(bookTableModel.getBooksList());
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    TestData.getInstance().saveToFile(set, fileToSave.getAbsolutePath());
                }
            }
        });

//       setLayout(new GridLayout());
        setLayout(new BorderLayout());
        add(bookScroller);
        JPanel buttonBookPanel = new JPanel();
        buttonBookPanel.add(BorderLayout.SOUTH, openDataButton);
        buttonBookPanel.add(BorderLayout.SOUTH, newBookButton);
        buttonBookPanel.add(BorderLayout.SOUTH, saveBookList);
        add(BorderLayout.SOUTH, buttonBookPanel);
    }


    public JTable getBookTable() {
        return bookTable;
    }

    public void setBookTable(JTable bookTable) {
        this.bookTable = bookTable;
    }

    public List<BookImpl> getListBooks() {
        return listBooks;
    }

    public void setListBooks(List<BookImpl> listBooks) {
        this.listBooks = listBooks;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == newBookButton) {
            AddBookDialog addBookDialog = new AddBookDialog(null, true);
            addBookDialog.setSize(new Dimension(500, 250));
            addBookDialog.setVisible(true);
            BookImpl newBook = addBookDialog.getNewBook();
            System.out.println(newBook);
            bookTableModel.addNewBook(newBook);
            bookTable.updateUI();
        }
    }
}
