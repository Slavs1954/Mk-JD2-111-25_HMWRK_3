package dto;

import java.util.List;
import java.util.Map;

public class Stats {
    private final Map<String, Integer> authors;
    private Map<String, Integer> genres;
    private final List<String> abouts;

    public Stats(Map<String,Integer> authors, Map<String, Integer> genres, List<String> abouts) {
        this.authors = authors;
        this.genres = genres;
        this.abouts = abouts;
    }

    public Map<String, Integer> getAuthors() {
        return authors;
    }

    public Map<String, Integer> getGenres() {
        return genres;
    }

    public List<String> getAbouts() {
        return abouts;
    }
}
