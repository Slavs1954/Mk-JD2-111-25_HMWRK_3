package service.api;

public enum EFeature {
    VOTE_STORAGE_TYPE("SQL");

    private final String defaultValue;

    EFeature(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefault() {
        return defaultValue;
    }
}
