package main.java.com.alexe1ka.view;

import main.java.com.alexe1ka.TestData;
import main.java.com.alexe1ka.model.BookImpl;
import main.java.com.alexe1ka.model.Genre;
import main.java.com.alexe1ka.model.MyTableModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BookPanel extends JPanel implements ActionListener {
    private JTable bookTable;
    private JScrollPane bookScroller;

    private Button openDataButton;
    private Button newBookButton;
    private Button editBookButton;
    private Button saveBookList;

    public List<BookImpl> listBooks;
    private MyTableModel bookTableModel;


    public BookPanel() {
        listBooks = new ArrayList<>();
        //listBooks.addAll(testData.readBookFromFile());// добавление тестовых данных
        bookTableModel = new MyTableModel(listBooks);


        //вкладка с книгами отображает таблицу со всеми книгами.
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
                    listBooks.clear();
                    try {
                        listBooks.addAll(TestData.getInstance().readBookFromFile(file.getAbsolutePath()));
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(getParent(), "Ошибка", "Error!", JOptionPane.ERROR_MESSAGE);
                    } catch (ClassNotFoundException e1) {
                        JOptionPane.showMessageDialog(getParent(), "Откройте файл с книгами", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    bookTable.updateUI();
                }
            }
        });


        newBookButton = new Button("New book");
        newBookButton.addActionListener(this);

        Set<BookImpl> saveSet = new TreeSet<>();

        saveBookList = new Button("Save new list");
        saveBookList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save books DB");
                int userSelection = fileChooser.showSaveDialog(null);

                saveSet.addAll(bookTableModel.getBooksList());
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    SwingWorker worker = new SwingWorker() {
                        @Override
                        protected Object doInBackground() throws Exception {
//                            Thread.sleep(1000);
                            TestData.getInstance().saveToFile(saveSet, fileToSave.getAbsolutePath());
                            return null;
                        }

                        @Override
                        protected void done() {
                            JOptionPane.showMessageDialog(getParent(), "Books list saved", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }

                        @Override
                        protected void process(List chunks) {
                            super.process(chunks);

                        }
                    };
                    worker.run();
                }
            }
        });

        editBookButton = new Button("Edit current book");
        editBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bookTable.getSelectedRow();
                AddBookDialog addBookDialog = new AddBookDialog(null,
                        true,
                        bookTableModel.getValueAt(selectedRow, 0),
                        bookTableModel.getValueAt(selectedRow, 1),
                        bookTableModel.getValueAt(selectedRow, 2),
                        bookTableModel.getValueAt(selectedRow, 3),
                        bookTableModel.getValueAt(selectedRow, 4));
                addBookDialog.setSize(new Dimension(500, 250));
                addBookDialog.setVisible(true);
//                bookTableModel.setValueAt("CHECK",selectedRow,0);
                BookImpl editBook = addBookDialog.getBook();
                if (editBook != null) {
                    System.out.println(editBook);
                    bookTableModel.editBook(editBook, selectedRow);
                    bookTable.updateUI();
                }
                updateUI();
            }
        });

//       setLayout(new GridLayout());
        setLayout(new BorderLayout());
        add(bookScroller);
        JPanel buttonBookPanel = new JPanel();
        buttonBookPanel.add(BorderLayout.SOUTH, openDataButton);
        buttonBookPanel.add(BorderLayout.SOUTH, newBookButton);
        buttonBookPanel.add(BorderLayout.SOUTH, editBookButton);
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
            BookImpl newBook = addBookDialog.getBook();
            if (newBook != null) {
                System.out.println(newBook);
                bookTableModel.addNewBook(newBook);
                bookTable.updateUI();
            }
        }
    }
}
