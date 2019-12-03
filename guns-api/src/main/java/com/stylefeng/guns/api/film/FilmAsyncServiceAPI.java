package com.stylefeng.guns.api.film;

import com.stylefeng.guns.api.film.vo.*;

import java.util.List;

public interface FilmAsyncServiceAPI {

    FilmDescVo getFilmDesc(String filmId);

    ImgVo getImgs(String filmId);


    ActorVo getDectInfo(String filmId);

    List<ActorVo> getActors(String filmId);

}
