package main.java.com.alexe1ka.model;

import java.io.Serializable;
import java.util.Date;

public class BookReader implements Comparable<BookReader>, Serializable {
    private String nameSurname;
    private BookImpl takingBook;
    private long phoneNumber;
    private String numberOfPassport;
    private Date takingBookDate;

    public BookReader() {
    }

    public BookReader(String nameSurname, BookImpl takingBook, long phoneNumber, String numberOfPassport, Date takingBookDate) {
        this.nameSurname = nameSurname;
        this.takingBook = takingBook;
        this.phoneNumber = phoneNumber;
        this.numberOfPassport = numberOfPassport;
        this.takingBookDate = takingBookDate;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public BookImpl getTakingBook() {
        return takingBook;
    }

    public void setTakingBook(BookImpl takingBook) {
        this.takingBook = takingBook;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNumberOfPassport() {
        return numberOfPassport;
    }

    public void setNumberOfPassport(String numberOfPassport) {
        this.numberOfPassport = numberOfPassport;
    }

    public Date getTakingBookDate() {
        return takingBookDate;
    }

    public void setTakingBookDate(Date takingBookDate) {
        this.takingBookDate = takingBookDate;
    }

    @Override
    public String toString() {
        return "BookReader{" +
                "nameSurname='" + nameSurname + '\'' +
                ", takingBook=" + takingBook.getTitle() +
                ", phoneNumber=" + phoneNumber +
                ", numberOfPassport='" + numberOfPassport + '\'' +
                ", takingBookDate=" + takingBookDate +
                '}';
    }

    public Object[] getFieldArray() {
        return new Object[]{nameSurname,
                takingBook.getTitle(),
                phoneNumber,
                numberOfPassport,
                takingBookDate};
    }

    @Override
    public int compareTo(BookReader o) {
        return this.nameSurname.compareTo(o.getNameSurname());
    }
}
