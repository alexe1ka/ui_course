package com.alexe1ka.view;

import com.alexe1ka.model.BookImpl;
import com.alexe1ka.model.Genre;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddBookDialog extends JDialog implements ActionListener {
    private JLabel titleLabel;
    private JLabel authorLabel;
    private JLabel yearLabel;
    private JLabel genreLabel;
    private JLabel pageCountLabel;

    private JTextField titleField;
    private JTextField authorField;
    private JFormattedTextField yearField;
    private JComboBox<Genre> genreBox;
    private JFormattedTextField pageCountField;

    private Button okButton;
    private BookImpl newBook;

    public AddBookDialog(Frame owner, boolean modal) {
        super(owner, modal);
        init();
    }

    public AddBookDialog(Frame owner,
                         boolean modal, Object valueAt, Object valueAt1, Object valueAt2, Object valueAt3, Object valueAt4, Object valueAt5) {
        super(owner,modal);
        
    }

    private void init() {

        titleLabel = new JLabel("Title");
        authorLabel = new JLabel("Author");
        yearLabel = new JLabel("Year");
        genreLabel = new JLabel("Genre");
        pageCountLabel = new JLabel("Pages");

        titleField = new JTextField();
        authorField = new JTextField();
        try {
            yearField = new JFormattedTextField(new MaskFormatter("####"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        genreBox = new JComboBox<>();

        try {
            pageCountField = new JFormattedTextField(new MaskFormatter("*###"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        okButton = new Button();

        Genre[] listOfEnumValue = Genre.values();
        for (Genre genre : listOfEnumValue) {
            genreBox.addItem(genre);
        }
        this.setLayout(new BorderLayout());
        this.setTitle("Add new book");

        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(6, 2));
        dataPanel.add(titleLabel);
        dataPanel.add(titleField);
        dataPanel.add(authorLabel);
        dataPanel.add(authorField);
        dataPanel.add(yearLabel);
        dataPanel.add(yearField);
        dataPanel.add(genreLabel);
        dataPanel.add(genreBox);
        dataPanel.add(pageCountLabel);
        dataPanel.add(pageCountField);
        dataPanel.add(okButton, 10);
        add(dataPanel, BorderLayout.CENTER);

        okButton.setLabel("Add");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        buttonPanel.add(okButton);
        okButton.addActionListener(this);
        add(buttonPanel, BorderLayout.SOUTH);
    }


    public BookImpl getNewBook() {
//        newBook.setTitle(titleField.getText());
//        newBook.setAuthors(authorField.getText());
//        newBook.setYearOfPublishing(yearField.getText());
//        newBook.setGenre((Genre) genreBox.getSelectedItem());
//        newBook.setPageCount(new Integer(pageCountField.getText()));
//        newBook.setInStorehouse(true);//раз мы добавляем новую книгу - она точно есть в хранилище
        return newBook;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == okButton) {
            if (titleField.getText().equals("") || authorField.getText().equals("") || yearField.getText().equals("") || pageCountField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "All fields must be input!");
            } else {
                newBook = new BookImpl(titleField.getText(),
                        authorField.getText(),
                        yearField.getText(),
                        (Genre) genreBox.getSelectedItem(),
                        new Integer(pageCountField.getText()),
                        true);
                dispose();
            }
        }

    }
}
