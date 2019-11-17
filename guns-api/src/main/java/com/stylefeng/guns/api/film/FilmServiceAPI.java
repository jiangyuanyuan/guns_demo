package com.stylefeng.guns.api.film;

import com.stylefeng.guns.api.film.vo.BannerVo;
import com.stylefeng.guns.api.film.vo.FilmInfoVo;
import com.stylefeng.guns.api.film.vo.FilmVo;

import java.util.List;

public interface FilmServiceAPI {

    //获取banner
    List<BannerVo> getBanners();

    //获取正在热映电影
    FilmVo getHotFilms(boolean isLimit ,int num);

    //获取即将上映的电影
    FilmVo getSoonFilms(boolean isLimit ,int num);

    //获取票房排行
    List<FilmInfoVo> getBoxRanking();

    //获取受欢迎排行
    List<FilmInfoVo> getExpectRanking();

    //获取top100
    List<FilmInfoVo> getTop();

}
