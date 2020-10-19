package controller;

/**
 * @author Sofia Ayata Karbin
 */

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
