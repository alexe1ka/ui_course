package com.alexe1ka.view;

import sun.plugin.javascript.JSClassLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReaderPanel extends JPanel {
    private DefaultTableModel model;
    private JTable readerTable;
    private JScrollPane readerScroller;
    private Button newReaderButton;

    public ReaderPanel() {
        super();
        model = new DefaultTableModel();
        readerTable = new JTable(model);
        model.addColumn("Reader name");
        model.addColumn("Taking book");
        model.addColumn("Phone number");
        model.addColumn("Number of passport");
        model.addColumn("Date of taking book");

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
        setLayout(new BorderLayout());
        add(readerScroller);//до этого панель просто была пустой


    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }
}
