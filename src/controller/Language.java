package controller;

public enum Language {
    SWEDISH("swedish"),
    ENGLISH("english"),
    NOT_SPECIFIED("not specified");

    private final String language;

    Language(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
