package com.alexe1ka;

import com.alexe1ka.model.BookImpl;
import com.alexe1ka.model.Genre;

import java.io.*;
import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

public class TestData {

    public void makeWork() {
        Set<BookImpl> books = new TreeSet<>();

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2017, 0, 0);
        books.add(new BookImpl("Android for professional 3", "Philips B,Stuart K,Marsikano k",
                calendar1.getTime(),
                Genre.SCIENCE,
                688,
                false));

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2016, 0, 0);
        books.add(new BookImpl("Android for professional 2", "Philips B,Stuart K,Marsikano k",
                calendar2.getTime(),
                Genre.SCIENCE,
                640,
                true));

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(1990, 0, 0);
        books.add(new BookImpl("Introduction to psychoanalize", "Zigmund Freid",
                calendar3.getTime(),
                Genre.SCIENCE,
                379,false));

        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(1990, 0, 0);
        books.add(new BookImpl("Introduction to psychoanalize", "Zigmund Freid",
                calendar4.getTime(),
                Genre.SCIENCE,
                379,true));

        Calendar calendar5 = Calendar.getInstance();
        calendar5.set(1997, 0, 0);
        books.add(new BookImpl("Harry Potter and the Philosopher's Stone", "Dj. Rouling",
                calendar5.getTime(),
                Genre.FAIRYTALE,
                500,true));

        Calendar calendar6 = Calendar.getInstance();
        calendar6.set(1997, 0, 0);
        books.add(new BookImpl("Core Java.Volume II - Advanced Features", "Cay Horstmann",
                calendar6.getTime(),
                Genre.TRILLER,
                500,false));


        for (Object book : books) {
            System.out.println(book);
        }

        saveToFile(books);

        Set<BookImpl> readedSet = readFromFile();
        System.out.println("set from file");
        for (Object aReadedSet : readedSet) {
            System.out.println(aReadedSet);
        }

    }

    public void saveToFile(Set<BookImpl> books) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("books"));
            objectOutputStream.writeObject(books);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<BookImpl> readFromFile() {
        Set<BookImpl> books = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("books"));
            books = (Set<BookImpl>) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return books;
    }
}
