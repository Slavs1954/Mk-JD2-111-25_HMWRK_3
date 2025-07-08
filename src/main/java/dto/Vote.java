package dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Vote {
    private LocalDateTime dateTimeCreate;
    private String author;
    private List<String> genres;
    private String about;


    public static Builder builder() {
        return new Builder();
    }
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

    private Vote(LocalDateTime dtCreate, String author,
                List<String> genres, String about) {
        this.dateTimeCreate = dtCreate;
        this.author = author;
        this.genres = genres;
        this.about = about;
    }
    public static class Builder {
        private LocalDateTime dtCreate = LocalDateTime.now();
        private String author;
        private List<String> genres = new ArrayList<>();
        private String about;

        public Builder dtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder addGenre(String genre) {
            this.genres.add(genre);
            return this;
        }
        public Builder addGenreList(List<String> genres) {
            this.genres.addAll(genres);
            return this;
        }

        public Builder about(String about) {
            this.about = about;
            return this;
        }

        public Vote build() {
            return new Vote(dtCreate, author, List.copyOf(genres), about);
        }
    }
}

