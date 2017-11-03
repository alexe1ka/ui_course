package com.alexe1ka;

import com.alexe1ka.view.LibraryGui;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        LibraryGui start = new LibraryGui();
        SwingUtilities.invokeLater(start::gui);
    }
}
