package storage;

import dto.Vote;
import edu.emory.mathcs.backport.java.util.Arrays;
import storage.api.IVoteStorage;
import storage.api.exceptions.StorageException;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VoteStorageSQL implements IVoteStorage {

    private final DataSource dataSource;
    public VoteStorageSQL(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void add(Vote vote) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement("""
                     INSERT INTO vote_app.votes(dt_create, author, genres, about)
                     VALUES (?, ?, ?, ?);
            """);
        ) {

            statement.setObject(1, vote.getDateTimeCreate());
            statement.setObject(2, vote.getAuthor());
            statement.setArray(3, conn.createArrayOf("varchar", vote.getGenres().toArray()));
            statement.setObject(4, vote.getAbout());

            statement.executeUpdate();
        } catch (Exception e){
            throw new StorageException("Error inserting vote");
        }
    }

    @Override
    public List<Vote> getAll() {
        List<Vote> retList = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement("""
                    SELECT dt_create, author, genres, about
                	FROM vote_app.votes;
            """);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                LocalDateTime dtCreate = resultSet.getObject("dt_create", LocalDateTime.class);
                String author = resultSet.getString("author");
                String[] genres = (String[]) resultSet.getArray("genres").getArray();
                String about = resultSet.getString("about");

                retList.add(Vote.builder()
                        .dtCreate(dtCreate)
                        .author(author)
                        .addGenreList(Arrays.asList(genres))
                        .about(about)
                        .build());
            }
        } catch (Exception e) {
            throw new StorageException("Failed to retrieve data from storage");
        }
        return retList;

    }
}
