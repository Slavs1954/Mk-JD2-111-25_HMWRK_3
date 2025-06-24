package dto;

import java.time.LocalDateTime;
import java.util.List;

public class Vote {
    private LocalDateTime dateTimeCreate;
    private String author;
    private List<String> genres;
    private String about;

    public LocalDateTime getDateTimeCreate() {
        return dateTimeCreate;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getAbout() {
        return about;
    }

    public void setDateTimeCreate(LocalDateTime dateTimeCreate) {
        this.dateTimeCreate = dateTimeCreate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
