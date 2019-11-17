package com.stylefeng.guns.api.film.vo;


import java.io.Serializable;

public class FilmInfoVo  implements Serializable {
//    {
//        filmId：’001’,
//        filmType：1, [0-2D,1-3D,2-3DIMAX,4-无]
//        imgAddress：‘img/film/001.jpg’
//        filmName：‘我不是药神’,
//        filmScore：‘8.3’
//    }

    public String filmId;
    public String filmType;
    public String imgAddress;
    public String filmName;
    public String filmScore;
    public String boxNum;

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmScore() {
        return filmScore;
    }

    public void setFilmScore(String filmScore) {
        this.filmScore = filmScore;
    }

    public String getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(String boxNum) {
        this.boxNum = boxNum;
    }
}
