package com.testcontainers.core.entity;

public enum HiddenVillage {

    IWAGAKURE("Iwagakure"), KUMOGAKURE("Kumogakure"), KIRIGAKURE("Kirigakure"),
    SUNAGAKURE ("Sunagakure"), KONOHAGAKURE("Konohagakure");


    private final String displayName;

    HiddenVillage(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
