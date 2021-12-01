package com.testcontainers.core.entity;

public enum ComicUniversum {

    MARVEL("Marvel"),
    DC_COMICS ("DC Comic");

    private final String displayName;

    ComicUniversum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
