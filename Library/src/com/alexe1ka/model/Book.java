package com.alexe1ka.model;

import java.util.Date;

public interface Book {
    //если что,заменю BookImpl на другую имплементацию
    String getTitle();

    String getAuthors();

    Date getYearOfPublishing();

    Genre getGenre();

    int getPageCount();


}
