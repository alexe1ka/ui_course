package main.java.com.alexe1ka;

import main.java.com.alexe1ka.view.LibraryGui;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
//        TestData.getInstance().testBook();
        LibraryGui start = new LibraryGui();
        SwingUtilities.invokeLater(start::gui);
    }
}
