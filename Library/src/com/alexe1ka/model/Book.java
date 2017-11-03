package com.alexe1ka.model;

public interface Book {
    String getTitle();

    String getAuthors();

    String getYearOfPublishing();

    Genre getGenre();

    Integer getPageCount();

    boolean isInStorehouse();

}
