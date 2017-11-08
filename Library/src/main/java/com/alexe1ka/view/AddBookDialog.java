package main.java.com.alexe1ka.view;

import main.java.com.alexe1ka.model.BookImpl;
import main.java.com.alexe1ka.model.Genre;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddBookDialog extends JDialog implements ActionListener {

    private JLabel titleLabel = new JLabel("Title");
    private JLabel authorLabel = new JLabel("Author");
    private JLabel yearLabel = new JLabel("Year");
    private JLabel genreLabel = new JLabel("Genre");
    private JLabel pageCountLabel = new JLabel("Pages");

    private JTextField titleField;
    private JTextField authorField;
    private JSpinner yearField;

    private JComboBox<Genre> genreBox;
    private JFormattedTextField pageCountField;

    private Button okButton;
    private BookImpl newBook;

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");//нам нужен только год
    private KeyAdapter yearKeyAdapter = new KeyAdapter() {
        //            @Override
//            public void keyTyped(KeyEvent e) {
//                String input = pageCountField.getText();
//                Pattern pattern = Pattern.compile("[0-9]{4}|[0-9]{3}|[0-9]{2}|[0-9]");
//                Matcher matcher = pattern.matcher(input);
//                if (!matcher.find()) {
//                    JOptionPane.showMessageDialog(getParent(), "Можно вводить только цифры", "Error!", JOptionPane.ERROR_MESSAGE);
//                    pageCountField.setText("");
//                }
//            }

        @Override
        public void keyPressed(KeyEvent e) {//если ввели букву - ошибка
            if (e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z') {
                JOptionPane.showMessageDialog(getParent(), "Можно вводить только цифры", "Error!", JOptionPane.ERROR_MESSAGE);
                pageCountField.setText("");
            } else {
                pageCountField.setEditable(true);
            }
        }
    };


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
        makeSpinner();

        genreBox = new JComboBox<>();

        try {
            pageCountField = new JFormattedTextField(new MaskFormatter("****"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        pageCountField.addKeyListener(yearKeyAdapter);

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

        makeSpinner();

        Date date = null;
        try {
            date = yearFormat.parse((String) valueAt2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        yearField.setValue(date);

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
            pageCountField = new JFormattedTextField(new MaskFormatter("****"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        pageCountField.setText(String.valueOf(valueAt4));
        pageCountField.addKeyListener(yearKeyAdapter);

        okButton = new Button();

        this.setLayout(new BorderLayout());
        this.setTitle("Edit book");

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

    private void makeSpinner() {
        SpinnerModel model = new SpinnerDateModel();
        yearField = new JSpinner(model);
        JComponent editor = new JSpinner.DateEditor(yearField, "yyyy");//Отображаем именно года
        yearField.setEditor(editor);
        JFormattedTextField tf = ((JSpinner.DefaultEditor) yearField.getEditor()).getTextField();
        tf.setEditable(false);//гарантированный выбор годов
    }


    public BookImpl getBook() {
        return newBook;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == okButton) {
            if (titleField.getText().equals("") || authorField.getText().equals("") || yearField.getValue().equals("") || pageCountField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "All fields must be input!");
            } else {
                System.out.println(pageCountField.getText());
                newBook = new BookImpl(titleField.getText(),
                        authorField.getText(),
                        yearFormat.format(yearField.getValue()),
                        (Genre) genreBox.getSelectedItem(),
                        new Integer(pageCountField.getText().trim()),
                        true);
                dispose();
            }
        }
    }
}
