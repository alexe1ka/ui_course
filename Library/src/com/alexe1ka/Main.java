package com.alexe1ka;

import com.alexe1ka.view.LibraryGui;

public class Main {

    public static void main(String[] args) {
        TestData testData = new TestData();
        testData.makeWork();
        LibraryGui start= new LibraryGui();
        start.gui();


    }
}
