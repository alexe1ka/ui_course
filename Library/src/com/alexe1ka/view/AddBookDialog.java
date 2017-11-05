package com.alexe1ka.view;

import com.alexe1ka.model.BookImpl;
import com.alexe1ka.model.Genre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookDialog extends JDialog implements ActionListener {
    private JLabel titleLabel;
    private JLabel authorLabel;
    private JLabel yearLabel;
    private JLabel genreLabel;
    private JLabel pageCountLabel;

    private JTextField titleField;
    private JTextField authorField;
    private JTextField yearField;
    private JComboBox<Genre> genreBox;
    private JTextField pageCountField;

    private Button okButton;
    private BookImpl newBook;

    private boolean isResultOk;


    public AddBookDialog(Frame owner, boolean modal) {
        super(owner, modal);
        init();
    }

    private void init() {
        isResultOk = false;

        titleLabel = new JLabel("Title");
        authorLabel = new JLabel("Author");
        yearLabel = new JLabel("Year");
        genreLabel = new JLabel("Genre");
        pageCountLabel = new JLabel("Pages");

        titleField = new JTextField();
        authorField = new JTextField();
        yearField = new JTextField();
        genreBox = new JComboBox<>();
        pageCountField = new JTextField();

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
            newBook = new BookImpl(titleField.getText(),
                    authorField.getText(),
                    yearField.getText(),
                    (Genre) genreBox.getSelectedItem(),
                    new Integer(pageCountField.getText()),
                    true);
        }
        else {
            newBook = new BookImpl(null,null,null,null,null,false);
        }
        dispose();
    }
}
