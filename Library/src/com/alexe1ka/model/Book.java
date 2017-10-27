package com.alexe1ka.model;

import java.util.Date;

public interface Book {
    String getTitle();

    String getAuthors();

    Date getYearOfPublishing();

    Genre getGenre();

    int getPageCount();

    boolean isInStorehouse();

}
