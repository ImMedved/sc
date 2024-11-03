package org.kukharev.core;

public class GlobalSettings {
    private static GlobalSettings instance;
    private String language;

    private GlobalSettings() {
        language = "en";
    }

    public static synchronized GlobalSettings getInstance() {
        if (instance == null) {
            instance = new GlobalSettings();
        }
        return instance;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
