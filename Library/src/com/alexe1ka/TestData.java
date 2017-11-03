package com.alexe1ka;

import com.alexe1ka.model.BookImpl;
import com.alexe1ka.model.BookReader;
import com.alexe1ka.model.Genre;

import java.io.*;
import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

public class TestData {
    BookImpl book1;
    BookImpl book3;
    private static TestData instance;

    public static TestData getInstance() {
        if (instance == null) {
            instance = new TestData();
        }
        return instance;
    }

    private TestData() {
    }


    //тестовый набор книжек
    public void testBook() {
        Set<BookImpl> books = new TreeSet<>();

        book1 = new BookImpl("Android for professional 3", "Philips B,Stuart K,Marsikano k",
                "2017",
                Genre.SCIENCE,
                688,
                false);
        books.add(book1);


        books.add(new BookImpl("Android for professional 2", "Philips B,Stuart K,Marsikano k",
                "2016",
                Genre.SCIENCE,
                640,
                true));


        book3 = new BookImpl("Introduction to psychoanalize", "Zigmund Freid",
                "1990",
                Genre.SCIENCE,
                379, false);
        books.add(book3);


        books.add(new BookImpl("Introduction to psychoanalize", "Zigmund Freid",
                "1990",
                Genre.SCIENCE,
                379, true));


        books.add(new BookImpl("Harry Potter and the Philosopher's Stone", "Dj. Rouling",
                "2000",
                Genre.FAIRYTALE,
                500, true));


        books.add(new BookImpl("Core Java.Volume II - Advanced Features", "Cay Horstmann",
                "2017",
                Genre.TRILLER,
                500, false));


        for (Object book : books) {
            System.out.println(book);
        }

        saveToFile(books, "books");

        Set<?> readedSet = readBookFromFile("books");
        System.out.println("set from file");
        for (Object aReadedSet : readedSet) {
            System.out.println(aReadedSet);
        }

    }

    public void testReader() {
        Set<BookReader> bookReaders = new TreeSet<>();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2000, Calendar.JUNE, 20);
        bookReaders.add(new BookReader(
                "Till Lindemann", book1, 89999555L, "99-9999", calendar1.getTime()
        ));
        Calendar calendar2 = Calendar.getInstance();
        calendar1.set(2017, Calendar.NOVEMBER, 30);
        bookReaders.add(new BookReader(
                "James Hetfield", book3, 8555999999L, "5555-111", calendar2.getTime()
        ));

        saveToFile(bookReaders, "readers");
        Set<?> readedSet = readReaderFromFile();
        System.out.println("readers from file");
        for (Object aReadedSet : readedSet) {
            System.out.println(aReadedSet);
        }
    }

    public void saveToFile(Set<?> objects, String filename) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOutputStream.writeObject(objects);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<BookImpl> readBookFromFile(String filename) {
        Set<BookImpl> books = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            books = (Set<BookImpl>) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Set<BookReader> readReaderFromFile() {
        Set<BookReader> readers = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("readers"));
            readers = (Set<BookReader>) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return readers;
    }
}
