package com.stylefeng.guns.rest.modular.films.vo;

import lombok.Data;

@Data
public class FilmInfoVo  {
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


}
