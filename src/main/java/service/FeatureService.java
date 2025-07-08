package service;

import dto.FeatureToggle;
import service.api.EFeature;
import service.api.IFeatureService;
import storage.api.IStorageSwitcher;

import java.util.Map;

public class FeatureService implements IFeatureService {
    private final Map<EFeature, IStorageSwitcher> switchers;

    public FeatureService(Map<EFeature, IStorageSwitcher> switchers) {
        this.switchers = switchers;
    }

    @Override
    public boolean toggle(FeatureToggle data) {
        IStorageSwitcher switcher = switchers.get(data.getFeature());

        if (switcher != null) {
            switcher.toggle(data.getValue());
            return true;
        }
        return false;
    }
}
