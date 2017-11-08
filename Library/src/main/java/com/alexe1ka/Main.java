package main.java.com.alexe1ka;

import main.java.com.alexe1ka.view.LibraryGui;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
//        TestData.getInstance().testBook();
//        TestData.getInstance().testReader();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        LibraryGui start = new LibraryGui();
        SwingUtilities.invokeLater(start::gui);
    }
}
