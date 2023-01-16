package wia2007.com.sqlitepractice;

import java.io.Serializable;

public class Book implements Serializable {
    String id;
    String title;
    String author;
    int pages;

    public Book(String id, String title, String author, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }
}
