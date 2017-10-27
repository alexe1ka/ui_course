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

    public void testBook() {
        Set<BookImpl> books = new TreeSet<>();

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2017, 0, 0);
        book1 = new BookImpl("Android for professional 3", "Philips B,Stuart K,Marsikano k",
                calendar1.getTime(),
                Genre.SCIENCE,
                688,
                false);
        books.add(book1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2016, 0, 0);
        books.add(new BookImpl("Android for professional 2", "Philips B,Stuart K,Marsikano k",
                calendar2.getTime(),
                Genre.SCIENCE,
                640,
                true));

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(1990, 0, 0);
        book3 = new BookImpl("Introduction to psychoanalize", "Zigmund Freid",
                calendar3.getTime(),
                Genre.SCIENCE,
                379, false);
        books.add(book3);

        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(1990, 0, 0);
        books.add(new BookImpl("Introduction to psychoanalize", "Zigmund Freid",
                calendar4.getTime(),
                Genre.SCIENCE,
                379, true));

        Calendar calendar5 = Calendar.getInstance();
        calendar5.set(1997, 0, 0);
        books.add(new BookImpl("Harry Potter and the Philosopher's Stone", "Dj. Rouling",
                calendar5.getTime(),
                Genre.FAIRYTALE,
                500, true));

        Calendar calendar6 = Calendar.getInstance();
        calendar6.set(1997, 0, 0);
        books.add(new BookImpl("Core Java.Volume II - Advanced Features", "Cay Horstmann",
                calendar6.getTime(),
                Genre.TRILLER,
                500, false));


        for (Object book : books) {
            System.out.println(book);
        }

        saveToFile(books, "book.txt");

        Set<?> readedSet = readBookFromFile();
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

        saveToFile(bookReaders,"readers");
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

    public Set<BookImpl> readBookFromFile() {
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
