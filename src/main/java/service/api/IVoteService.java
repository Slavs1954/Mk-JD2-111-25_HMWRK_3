package service.api;

import dto.Stats;
import dto.Vote;

public interface IVoteService {
    void add(Vote vote);
    Stats getStats();
}
