package com.alexe1ka.view;

import com.alexe1ka.TestData;
import com.alexe1ka.model.BookImpl;
import com.alexe1ka.model.BookReader;
import sun.plugin.javascript.JSClassLoader;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ReaderPanel extends JPanel {
    private DefaultTableModel model;
    private JTable readerTable;
    private JScrollPane readerScroller;
    private Button newReaderButton;
    private Button saveReaderList;
    private List<BookImpl> usesBooksList;
    private JComboBox comboBox;

    public ReaderPanel() {
        //вкладка с читателями отображает вкладку с читателями,которые взяли книги из хранилища
        //здесь другой способ формирования таблицы - с помощью DefaultTableModel
        //чтобы нормально раскидать BookReader по столбцам,написан костыль в виде метода getFieldArray
        super();


        List<BookReader> readerList = new ArrayList<>();
        readerList.addAll(TestData.getInstance().readReaderFromFile());

        model = new DefaultTableModel();
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


        //выбор книги для читателя
//        comboBox = new JComboBox();
//        usesBooksList = BookPanel.getListBooks();
//        for (int i = 0; i < usesBooksList.size(); i++) {
//            comboBox.addItem(usesBooksList.get(i).getTitle());
//        }
//        readerTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
        readerTable.getColumnModel().getColumn(1).setCellEditor(new BookComboBox());


        readerTable.setRowHeight(30);

        readerScroller = new JScrollPane(readerTable);
        readerScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        readerScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        newReaderButton = new Button("Add new reader");
        newReaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.insertRow(model.getRowCount(), new Object[]{null, null, null, null, null});
            }
        });

        saveReaderList = new Button("Save new list");


        setLayout(new BorderLayout());
        add(readerScroller);//до этого панель просто была пустой
        JPanel buttonReaderPanel = new JPanel();
        buttonReaderPanel.setLayout(new GridLayout(10, 0));
        buttonReaderPanel.add(BorderLayout.NORTH, newReaderButton);
        buttonReaderPanel.add(BorderLayout.CENTER, saveReaderList);
        add(BorderLayout.EAST, buttonReaderPanel);
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }
}
