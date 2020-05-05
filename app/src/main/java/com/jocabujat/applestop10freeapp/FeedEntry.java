package com.jocabujat.applestop10freeapp;

import androidx.annotation.NonNull;

public class FeedEntry {

    private String name;
    private String artist;
    private String releaseDate;
    private String summary;
    private String imageURL;

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    void setArtist(String artist) {
        this.artist = artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSummary() {
        return summary;
    }

    void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImageURL() {
        return imageURL;
    }

    void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @NonNull
    @Override
    public String toString() {
        return "name = " + name + '\n' +
                ", artist = " + artist + '\n' +
                ", releaseDate = " + releaseDate + '\n' +
                ", imageURL = " + imageURL + '\n';
    }
}
