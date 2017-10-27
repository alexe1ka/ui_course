package com.alexe1ka.model;

import java.io.Serializable;
import java.util.Date;

public class BookReader implements Serializable {
    private String nameSurname;
    private int phoneNumber;
    private String numberOfPassport;
    private Date takingBookDate;

    public BookReader() {
    }

    public BookReader(String nameSurname, int phoneNumber, String numberOfPassport, Date takingBookDate) {
        this.nameSurname = nameSurname;
        this.phoneNumber = phoneNumber;
        this.numberOfPassport = numberOfPassport;
        this.takingBookDate = takingBookDate;
    }
}
