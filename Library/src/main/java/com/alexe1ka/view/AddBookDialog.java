package main.java.com.alexe1ka.view;

import main.java.com.alexe1ka.model.BookImpl;
import main.java.com.alexe1ka.model.Genre;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class AddBookDialog extends JDialog implements ActionListener {

    private JLabel titleLabel = new JLabel("Title");
    private JLabel authorLabel = new JLabel("Author");
    private JLabel yearLabel = new JLabel("Year");
    private JLabel genreLabel = new JLabel("Genre");
    private JLabel pageCountLabel = new JLabel("Pages");

    private JTextField titleField;
    private JTextField authorField;
    private JFormattedTextField yearField;
    private JComboBox<Genre> genreBox;
    private JFormattedTextField pageCountField;

    private Button okButton;
    private BookImpl newBook;

    public AddBookDialog(Frame owner, boolean modal) {
        super(owner, modal);
        addInit();
    }

    public AddBookDialog(Frame owner,
                         boolean modal, Object valueAt, Object valueAt1, Object valueAt2, Object valueAt3, Object valueAt4) {
        super(owner, modal);
        editInit(valueAt, valueAt1, valueAt2, valueAt3, valueAt4);
    }

    private void addInit() {
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

    private void editInit(Object valueAt, Object valueAt1, Object valueAt2, Object valueAt3, Object valueAt4) {
        titleField = new JTextField((String) valueAt);
        authorField = new JTextField((String) valueAt1);
        try {
            yearField = new JFormattedTextField(new MaskFormatter("####"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        yearField.setText((String) valueAt2);

        genreBox = new JComboBox<>();
        Genre[] listOfEnumValue = Genre.values();
        for (Genre genre : listOfEnumValue) {
            genreBox.addItem(genre);
        }
        for (int i = 0; i < listOfEnumValue.length; i++) {
            if (listOfEnumValue[i] == valueAt3) {
                genreBox.setSelectedIndex(i);
            }
        }


        try {
            pageCountField = new JFormattedTextField(new MaskFormatter("*###"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        pageCountField.setText(String.valueOf(valueAt4));


        okButton = new Button();


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


    public BookImpl getBook() {
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
