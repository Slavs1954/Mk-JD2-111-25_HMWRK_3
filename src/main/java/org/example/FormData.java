package org.example;

import java.time.LocalDateTime;
import java.util.*;

public class FormData {
    private String author;
    private List<String> genres;
    private String about;
    private LocalDateTime timeStamp;

    FormData(String author, List<String> genres, String customMessage) {
        this.author = author;
        this.genres = new ArrayList<>(genres);
        this.about = customMessage;
        this.timeStamp = LocalDateTime.now();
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

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
