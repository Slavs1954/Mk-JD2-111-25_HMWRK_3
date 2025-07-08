package service;

import service.api.EFeature;
import service.api.IFeatureService;
import service.api.IVoteService;
import storage.StorageFactory;
import storage.api.IStorageSwitcher;

import java.util.EnumMap;
import java.util.Map;

public class ServiceFactory {

    private final static IVoteService voteService = new VoteService(
            StorageFactory.getVoteStorage(),
            null
    );

    private final static IFeatureService featureService;

    static {
        Map<EFeature, IStorageSwitcher> switchers =
                new EnumMap<>(EFeature.class);
        switchers.put(EFeature.VOTE_STORAGE_TYPE,
                StorageFactory.getVoteStorage());

        featureService = new FeatureService(switchers);
    }

    private ServiceFactory() {}

    public static IVoteService getVoteService() {
        return voteService;
    }
    public static IFeatureService getFeatureService() {
        return featureService;
    }
}
