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
    private JPanel bookPanel;
    private JPanel readerPanel;
    private JPanel jPanel;
    private JTable bookTable;
    private JScrollPane bookScroller;

    private JTable readerTable;
    private JScrollPane readerScroller;

    private Button newBookButton;
    private Button newReaderButton;

    private Button saveBookList;
    private Button saveReaderList;


    public void gui() {
        TestData testData = TestData.getInstance();
        List<BookImpl> listBooks = new ArrayList<>();
        //listBooks.addAll(testData.readBookFromFile());// добавление тестовых данных
        MyTableModel bookTableModel = new MyTableModel(listBooks);


        List<BookReader> readerList = new ArrayList<>();
        readerList.addAll(testData.readReaderFromFile());

        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
//        frame.setResizable(false); //запрет на изменение размера фрейма

        //создаем две панели - одна для книжек,другая для читателей
        bookPanel = new JPanel();
        readerPanel = new JPanel();

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


        //вкладка с читателями отображает вкладку с читателями,которые взяли книги из хранилища
        //здесь другой способ формирования таблицы - с помощью DefaultTableModel
        //чтобы нормально раскидать BookReader по столбцам,написан костыль в виде метода getFieldArray
        //TODO второй таблицы в Book сделать cellEditor со списком книг из первой таблицы
        DefaultTableModel model = new DefaultTableModel();
        readerTable = new JTable(model);
        readerTable.setFont(font);
        model.addColumn("Reader name");
        model.addColumn("Taking book");
        model.addColumn("Phone number");
        model.addColumn("Number of passport");
        model.addColumn("Date of taking book");

        for (int i = 0; i < readerList.size(); i++) {
            model.insertRow(i, readerList.get(i).getFieldArray());
        }
        model.fireTableDataChanged();
        readerTable.setRowHeight(30);

        readerScroller = new JScrollPane(readerTable);
        readerScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        readerScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        newBookButton = new Button("New book");
        newBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookTableModel.addNewBook(new BookImpl(null, null, null, null, null, false));//создаю пустую книгу
                bookTable.updateUI();
            }
        });

        newReaderButton = new Button("Add new reader");
        newReaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.insertRow(model.getRowCount(), new Object[]{null, null, null, null, null});
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

        saveReaderList = new Button("Save new list");

        //вкладки
//        bookPanel.setLayout(new GridLayout());
        bookPanel.setLayout(new BorderLayout());
        bookPanel.add(bookScroller);
        JPanel buttonBookPanel = new JPanel();
        buttonBookPanel.add(BorderLayout.SOUTH, newBookButton);
        buttonBookPanel.add(BorderLayout.SOUTH, saveBookList);

        bookPanel.add(BorderLayout.SOUTH, buttonBookPanel);


        readerPanel.setLayout(new BorderLayout());
        readerPanel.add(readerScroller);
        JPanel buttonReaderPanel = new JPanel();
        buttonReaderPanel.setLayout(new GridLayout(10, 0));
        buttonReaderPanel.add(BorderLayout.NORTH, newReaderButton);
        buttonReaderPanel.add(BorderLayout.CENTER, saveReaderList);
        readerPanel.add(BorderLayout.EAST, buttonReaderPanel);


        jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("Books", bookPanel);

        //TODO ВЫТАЩИТЬ ДВЕ ПАНЕЛИ
        ReaderPanel readerPanel1 = new ReaderPanel();
        jTabbedPane.addTab("Readers", readerPanel1);
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
                    listBooks.addAll(testData.readBookFromFile(file.getAbsolutePath()));
                    bookTable.updateUI();
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
