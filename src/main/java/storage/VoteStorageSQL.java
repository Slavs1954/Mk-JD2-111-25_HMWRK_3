package storage;

import dto.Vote;
import edu.emory.mathcs.backport.java.util.Arrays;
import storage.api.IVoteStorage;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VoteStorageSQL implements IVoteStorage {

    // TODO: Move auth and url to separate layer
    private final static String url = "jdbc:postgresql://localhost:5432/vote";

    @Override
    public void add(Vote vote) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Properties props = new Properties();

        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        props.setProperty("ssl", "false");

        try (Connection conn = DriverManager.getConnection(url, props);
             Statement statement = conn.createStatement();
        ) {

            statement.execute("""
                INSERT INTO vote_app.votes(dt_create, author, genres, about)
                	VALUES (now(), '%s', '%s', '%s');
            """.formatted(vote.getAuthor(), conn.createArrayOf("varchar",vote.getGenres().toArray()), vote.getAbout()));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Vote> getAll() {
        List<Vote> retList = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Properties props = new Properties();

        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        props.setProperty("ssl", "false");
        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement statement = conn.prepareStatement("""
                    SELECT dt_create, author, genres, about
                	FROM vote_app.votes;
            """);) {
        try (ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                LocalDateTime dtCreate = resultSet.getObject("dt_create", LocalDateTime.class);
                String author = resultSet.getString("author");
                String[] genres = (String[]) resultSet.getArray("genres").getArray();
                String about = resultSet.getString("about");

                Vote vote = new Vote();
                vote.setDateTimeCreate(dtCreate);
                vote.setAuthor(author);
                vote.setGenres(Arrays.asList(genres));
                vote.setAbout(about);
                retList.add(vote);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retList;

    }
}
