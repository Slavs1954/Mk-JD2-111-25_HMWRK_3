package service;

import dto.Stats;
import dto.Vote;
import service.api.IValidator;
import service.api.IVoteService;
import storage.VoteStorageRam;
import storage.VoteStorageSQL;
import storage.api.IVoteStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteService implements IVoteService {
    private final IVoteStorage storage;
    private final IValidator<Vote> validator;
    @Override
    public void add(Vote vote) {
        if(validator != null) {
            validator.validate(vote);
        }
        this.storage.add(vote);
    }

    public VoteService(IVoteStorage storage, IValidator<Vote> validator) {
        this.storage = storage;
        this.validator = validator;
    }

    @Override
    public Stats getStats() {
        Map<String, Integer> authorStats = new HashMap<>();
        Map<String, Integer> genresStats = new HashMap<>();
        List<String> abouts = new ArrayList<>();

        List<Vote> all = storage.getAll();
        for (Vote vote : all) {
            authorStats.compute(vote.getAuthor(), (k, v) ->
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
