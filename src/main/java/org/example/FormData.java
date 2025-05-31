package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FormData {
    private String author;
    private List<String> genres;
    private String about;

    FormData(String author, List<String> genres, String customMessage) {
        this.author = author;
        this.genres = new ArrayList<>(genres);
        this.about = customMessage;
    }

    public String getAuthor() {
        return author;
    }

    public Collection<String> getGenres() {
        return genres;
    }

    public String getAbout() {
        return about;
    }
}
