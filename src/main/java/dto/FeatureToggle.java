package dto;

import service.api.EFeature;

public class FeatureToggle {
    private final EFeature feature;
    private final String value;

    public FeatureToggle(EFeature feature, String value) {
        this.feature = feature;
        this.value = value;
    }

    public EFeature getFeature() {
        return feature;
    }

    public String getValue() {
        return value;
    }
}

