package com.alexe1ka.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookImpl implements Book, Comparable<BookImpl>, Serializable {
    private String title;
    private String authors;
    private Date yearOfPublishing;//здесь используется дата,только потому что это написано в задании.
    private Genre genre;
    private int pageCount;

    private int version = 1;

    public BookImpl() {
    }

    public BookImpl(String title, String author, Date yearOfPublishing, Genre genre, int pageCount) {
        this.title = title;
        this.authors = author;
        this.yearOfPublishing = yearOfPublishing;
        this.genre = genre;
        this.pageCount = pageCount;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public Date getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(Date yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookImpl book = (BookImpl) o;

        if (!title.equals(book.title)) return false;
        return authors.equals(book.authors);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + authors.hashCode();
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String onlyYear = dateFormat.format(yearOfPublishing);

        return "Book{" +
                "title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", yearOfPublishing=" + onlyYear +
                ", genre=" + genre +
                ", pageCount=" + pageCount +
                '}';
    }

    @Override
    public int compareTo(BookImpl o) {
        return title.compareTo(o.getTitle());
    }
}
