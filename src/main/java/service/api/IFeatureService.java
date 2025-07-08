package service.api;

import dto.FeatureToggle;

public interface IFeatureService {
    boolean toggle(FeatureToggle data);
}
