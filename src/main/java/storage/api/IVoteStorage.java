package storage.api;

import dto.Vote;

import java.util.List;

public interface IVoteStorage {
    void add(Vote vote);
    List<Vote> getAll();
}
