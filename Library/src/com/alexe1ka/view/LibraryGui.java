package com.alexe1ka.view;

import com.alexe1ka.TestData;
import com.alexe1ka.model.*;
import com.sun.org.apache.xpath.internal.operations.String;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class LibraryGui {
    private JFrame frame;
    private JTabbedPane jTabbedPane;
    private JPanel jPanel;
    private JTable bookTable;
    private JScrollPane bookScroller;

    private JTable readerTable;
    private JScrollPane readerScroller;

    private Button newBookButton;

    private int count = 0;


    public void gui() {
        TestData testData = new TestData();
//        Set<BookImpl> books = testData.readBookFromFile();
        List<BookImpl> listBooks = new ArrayList<>();
        listBooks.addAll(testData.readBookFromFile());
        MyTableModel bookTableModel = new MyTableModel(listBooks);

        List<BookReader> readerList = new ArrayList<>();
        readerList.addAll(testData.readReaderFromFile());

        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setResizable(false);

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

        Font font = new Font("TimesNewRoman", Font.BOLD, 14);
        bookTable.setFont(font);
        bookScroller = new JScrollPane(bookTable);
        bookScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        bookScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
        model.addColumn("Reader name");
        model.addColumn("Taking book");
        model.addColumn("Phone number");
        model.addColumn("Number of passport");
        model.addColumn("Date of taking book");


        for (int i = 0; i < readerList.size(); i++) {
            model.insertRow(i, readerList.get(i).getFieldArray());
        }
        model.fireTableDataChanged();

        readerScroller = new JScrollPane(readerTable);
        readerScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        readerScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        newBookButton = new Button("Edit bookTable");
        newBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(1997, 0, 0);
//                Book book = new BookImpl("Core Java", "Cay Horstmann",
//                        calendar.getTime(),
//                        Genre.TRILLER,
//                        500, false);
//                bookTableModel.setValueAt(book, bookTable.getRowCount() - 1, bookTable.getColumnCount());
                model.insertRow(model.getRowCount(), new Object[]{null, null, null, null, null});


            }
        });


        //вкладки
        jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("Books", bookScroller);
        jTabbedPane.addTab("Readers", readerScroller);
        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jTabbedPane);
        jPanel.add(BorderLayout.SOUTH, newBookButton);
        //меню действий
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
        frame.getContentPane().add(jPanel);
        frame.setVisible(true);

    }


}
