package com.stylefeng.guns.api.film.vo;

public class InfoReqVo {
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public ActorReqVo getActors() {
        return actors;
    }

    public void setActors(ActorReqVo actors) {
        this.actors = actors;
    }

    public ImgVo getImgVo() {
        return imgVo;
    }

    public void setImgVo(ImgVo imgVo) {
        this.imgVo = imgVo;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    private String biography;
    private ActorReqVo actors;
    private ImgVo imgVo;
    private String filmId;


}
