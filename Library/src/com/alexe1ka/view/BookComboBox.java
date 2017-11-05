package com.alexe1ka.view;

import com.alexe1ka.model.BookImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class BookComboBox extends DefaultCellEditor {

    // Declare a model that is used for adding the elements to the `ComboBox`
    private DefaultComboBoxModel model;

    private List<String> obtainedList;

    public BookComboBox() {
        super(new JComboBox());
        this.model = (DefaultComboBoxModel) ((JComboBox) getComponent()).getModel();

//        obtainedList = new ArrayList<>();
//        for (int i = 0; i < BookPanel.getListBooks().size(); i++) {
//            obtainedList.add(BookPanel.getListBooks().get(i).getTitle());
//        }

        //TODO сделать названия книг
        obtainedList = new ArrayList<String>();

        obtainedList.add("Book one");
        obtainedList.add("Book two");
        obtainedList.add("Book three");
        obtainedList.add("Book four");
        obtainedList.add("Book five");

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {


        if (column == 0) {
            model.removeAllElements();
            for (int i = 0; i < obtainedList.size(); i++) {
                model.addElement(obtainedList.get(i));
            }
        } else {

            model.removeAllElements();
            String selectedItem = (String) table.getValueAt(row, 0);
            for (int i = 0; i < obtainedList.size(); i++) {
                if (!selectedItem.equals(obtainedList.get(i)))
                    model.addElement(obtainedList.get(i));
            }
        }
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
}
