package com.alexe1ka.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyTableModel implements TableModel {

    //my table model работает в связке с моей моделью
    private Set<TableModelListener> listeners = new HashSet<>();

    private List<BookImpl> books = null;

    public MyTableModel(List<BookImpl> books) {
        this.books = books;
    }

    public void addNewBook(BookImpl book) {
        books.add(book);
    }

    @Override
    public int getRowCount() {
//        System.out.println("size of books: "+books.size());
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Title";
            case 1:
                return "Authors";
            case 2:
                return "Year";
            case 3:
                return "Genre";
            case 4:
                return "Pages";
            case 5:
                return "Availability";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Genre.class;
            case 4:
                return Integer.class;
            case 5:
                return Boolean.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }//TODO запрет на редактирование.сделать переключение

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return book.getTitle();
            case 1:
                return book.getAuthors();
            case 2:
                return book.getYearOfPublishing();
            case 3:
                return book.getGenre();
            case 4:
                return book.getPageCount();
            case 5:
                return book.isInStorehouse();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        BookImpl book = books.get(rowIndex);
        switch (columnIndex) {
            case 0:
                book.setTitle((String) aValue);
                break;
            case 1:
                book.setAuthors((String) aValue);
                break;
            case 2:
                book.setYearOfPublishing((String) aValue);
                break;
            case 3:
                book.setGenre((Genre) aValue);
                break;
            case 4:
                book.setPageCount((int) aValue);
                break;
            case 5:
                book.setInStorehouse((boolean) aValue);
                break;
        }
    }


    public List<BookImpl> getBooksList() {
        return books;
    }

    public void setBooks(List<BookImpl> books) {
        this.books = books;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }
}
