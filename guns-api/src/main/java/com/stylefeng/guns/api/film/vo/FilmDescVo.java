package com.stylefeng.guns.api.film.vo;

import java.io.Serializable;

public class FilmDescVo implements Serializable {
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    private String biography;
    private String filmId;

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }
}
