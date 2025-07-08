package storage;

import dto.Vote;
import service.api.EFeature;
import storage.api.EVoteStorageType;
import storage.api.IStorageSwitcher;
import storage.api.IVoteStorage;

import java.util.List;
import java.util.Map;

public class VoteStorage implements IVoteStorage, IStorageSwitcher {
    private final Map<EVoteStorageType, IVoteStorage> storage;
    private static EVoteStorageType currentType;

    public VoteStorage(Map<EVoteStorageType, IVoteStorage> storage,
                       EFeature defaultFeature) {
        this.storage = storage;
        toggle(defaultFeature.getDefault());
    }

    @Override
    public void add(Vote vote) {
        this.getStorage().add(vote);
    }

    @Override
    public List<Vote> getAll() {
        return  getStorage().getAll();
    }

    @Override
    public void toggle(String value) {
        currentType = EVoteStorageType.valueOf(value);
    }

    private IVoteStorage getStorage() {
        return  this.storage.get(currentType);
    }
}
