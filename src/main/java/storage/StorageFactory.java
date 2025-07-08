package storage;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import service.api.EFeature;
import storage.api.EVoteStorageType;
import storage.api.exceptions.StorageException;

import javax.sql.DataSource;
import java.util.EnumMap;

public class StorageFactory {
    private final static DataSource dataSource;

    static {
        try {
            ComboPooledDataSource cdps = new ComboPooledDataSource();
            cdps.setDriverClass("org.postgresql.Driver");
            cdps.setJdbcUrl("jdbc:postgresql://localhost:5432/vote");
            cdps.setUser("postgres");
            cdps.setPassword("postgres");

            dataSource = cdps;
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    private final static VoteStorage voteStorage;

    static {
        voteStorage = new VoteStorage(new EnumMap<>(EVoteStorageType.class) {{
            put(EVoteStorageType.SQL, new VoteStorageSQL(dataSource));
            put(EVoteStorageType.RAM, new VoteStorageRam());
        }}, EFeature.VOTE_STORAGE_TYPE);
    }

    private StorageFactory() {}

    public static VoteStorage getVoteStorage() {
        return voteStorage;
    }
}
