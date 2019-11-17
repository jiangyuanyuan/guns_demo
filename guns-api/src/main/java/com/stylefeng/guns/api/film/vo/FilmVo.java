package com.stylefeng.guns.api.film.vo;


import java.io.Serializable;
import java.util.List;


public class FilmVo implements Serializable {
    public String filmNum;
    public List<FilmInfoVo> filmInfo;

    public String getFilmNum() {
        return filmNum;
    }

    public void setFilmNum(String filmNum) {
        this.filmNum = filmNum;
    }

    public List<FilmInfoVo> getFilmInfo() {
        return filmInfo;
    }

    public void setFilmInfo(List<FilmInfoVo> filmInfo) {
        this.filmInfo = filmInfo;
    }
}
