package main.java.com.alexe1ka.view;


import main.java.com.alexe1ka.TestData;
import main.java.com.alexe1ka.model.BookImpl;
import main.java.com.alexe1ka.model.BookReader;

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

public class ReaderPanel extends JPanel {
    private DefaultTableModel model;
    private JTable readerTable;
    private JScrollPane readerScroller;

    private Button newReaderButton;
    private Button saveReaderList;
    private Button openReaderList;
    private Button editCurrentReader;

    private List<BookImpl> usesBooksList;
    private JComboBox comboBox;

    public ReaderPanel() {
        //вкладка с читателями отображает вкладку с читателями,которые взяли книги из хранилища
        //здесь другой способ формирования таблицы - с помощью DefaultTableModel
        //чтобы нормально раскидать BookReader по столбцам,написан костыль в виде метода getFieldArray

        //TODO ЭТА ТАБЛИЦА НЕ РАБОТАЕТ!!!она только отображает данные!
        //ДИАЛОГОВ для добавления/редактирования тут нет!

        super();


        List<BookReader> readerList = new ArrayList<>();
//        readerList.addAll(TestData.getInstance().readReaderFromFile("readers"));//загрузка тестовых данных

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
                readerList.add(new BookReader(null,null,null,null,null));
            }
        });

        saveReaderList = new Button("Save reader list");
        saveReaderList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save readers DB");
                int userSelection = fileChooser.showSaveDialog(null);
                Set<BookReader> set = new TreeSet<>();
                set.addAll(readerList);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    new SwingWorker(){
                        @Override
                        protected Object doInBackground() throws Exception {
                            TestData.getInstance().saveToFile(set, fileToSave.getAbsolutePath());
                            return null;
                        }

                        @Override
                        protected void done() {
                            JOptionPane.showMessageDialog(getParent(), "List of readers saved", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    };

                }
            }
        });

        openReaderList = new Button("Open readers list");
        openReaderList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showDialog(null, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    readerList.clear();
                    readerList.addAll(TestData.getInstance().readReaderFromFile(file.getAbsolutePath()));
                    for (int i = 0; i < readerList.size(); i++) {
                        model.insertRow(i, readerList.get(i).getFieldArray());
                    }

                }
            }
        });



        editCurrentReader = new Button("Edit current ");


        editCurrentReader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        setLayout(new BorderLayout());
        add(readerScroller);//до этого панель просто была пустой
        JPanel buttonReaderPanel = new JPanel();
        buttonReaderPanel.setLayout(new GridLayout(10, 0));
        buttonReaderPanel.add(BorderLayout.NORTH, newReaderButton);
        buttonReaderPanel.add(BorderLayout.CENTER, saveReaderList);
        buttonReaderPanel.add(BorderLayout.CENTER, openReaderList);
        buttonReaderPanel.add(BorderLayout.CENTER, editCurrentReader);
        add(BorderLayout.EAST, buttonReaderPanel);
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }
}
