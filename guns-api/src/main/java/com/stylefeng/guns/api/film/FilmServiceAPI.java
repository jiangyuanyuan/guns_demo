package com.stylefeng.guns.api.film;

import com.stylefeng.guns.api.film.vo.*;

import java.util.List;

public interface FilmServiceAPI {

    //获取banner
    List<BannerVo> getBanners();

    //获取正在热映电影
    FilmVo getHotFilms(boolean isLimit ,int page,int num,String sortId,String sourceId,String yearId,String catId);

    //获取即将上映的电影
    FilmVo getSoonFilms(boolean isLimit ,int page,int num,String sortId,String sourceId,String yearId,String catId);

    //获取经典影片
    FilmVo getClassicFilms(boolean isLimit ,int page,int num,String sortId,String sourceId,String yearId,String catId);

    //获取票房排行
    List<FilmInfoVo> getBoxRanking();

    //获取受欢迎排行
    List<FilmInfoVo> getExpectRanking();

    //获取top100
    List<FilmInfoVo> getTop();


    //分类条件
    List<CatInfoVo> getCats();


    //片源条件
    List<SouceVo> getSouces();

    //年代条件
    List<YearVo> getYear();
}
