package service;

import dto.Stats;
import dto.Vote;
import service.api.IVoteService;
import storage.VoteStorageRam;
import storage.api.IVoteStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteService implements IVoteService {
    private final IVoteStorage storage = new VoteStorageRam();
    private static VoteService instance = null;
    @Override
    public void add(Vote vote) {
        this.storage.add(vote);
    }

    private VoteService() {
    }
    public static VoteService getInstance() {
        if (instance == null) {
            instance = new VoteService();
        }
        return instance;
    }

    @Override
    public Stats getStats() {
        Map<String, Integer> authorStats = new HashMap<>();
        Map<String, Integer> genresStats = new HashMap<>();
        List<String> abouts = new ArrayList<>();

        List<Vote> all = storage.getAll();
        for (Vote vote : all) {
            authorStats.compute(vote.getArtist(), (k,v) ->
                v == null ? 1 : v + 1);
            for (String genre : vote.getGenres()) {
                genresStats.compute(genre, (k,v) ->
                        v == null ? 1 : v + 1);
            }
            abouts.add(vote.getDateTimeCreate() + ": " + vote.getAbout());
        }
        return new Stats(authorStats, genresStats, abouts);


    }

}
