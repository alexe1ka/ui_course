package com.alexe1ka;

import com.alexe1ka.model.Book;
import com.alexe1ka.model.BookImpl;
import com.alexe1ka.model.Genre;

import java.io.*;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TestData {

    public void makeWork() {
        Set<Book> books = new TreeSet<>();

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2017, 0, 0);
        books.add(new BookImpl("Android for professional 3", "Philips B,Stuart K,Marsikano k",
                calendar1.getTime(),
                Genre.SCIENCE,
                688));

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2016, 0, 0);
        books.add(new BookImpl("Android for professional 2", "Philips B,Stuart K,Marsikano k",
                calendar2.getTime(),
                Genre.SCIENCE,
                640));

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(1990, 0, 0);
        books.add(new BookImpl("Introduction to psychoanalize", "Zigmund Freid",
                calendar2.getTime(),
                Genre.SCIENCE,
                379));

        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(1990, 0, 0);
        books.add(new BookImpl("Introduction to psychoanalize", "Zigmund Freid",
                calendar2.getTime(),
                Genre.SCIENCE,
                379));

        Calendar calendar5 = Calendar.getInstance();
        calendar5.set(1997, 0, 0);
        books.add(new BookImpl("Harry Potter and the Philosopher's Stone", "Dj. Rouling",
                calendar2.getTime(),
                Genre.FAIRYTALE,
                500));

        Calendar calendar6 = Calendar.getInstance();
        calendar6.set(1997, 0, 0);
        books.add(new BookImpl("Core Java.Volume II - Advanced Features", "Cay Horstmann",
                calendar2.getTime(),
                Genre.TRILLER,
                500));


        for (Object book : books) {
            System.out.println(book);
        }

        saveToFile(books);

        Set<Book> readedSet = readFromFile();
        System.out.println("set from file");
        for (Object aReadedSet : readedSet) {
            System.out.println(aReadedSet);
        }

    }

    private void saveToFile(Set<Book> books) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("books"));
            objectOutputStream.writeObject(books);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<Book> readFromFile() {
        Set<Book> books = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("books"));
            books = (Set<Book>) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return books;
    }
}
