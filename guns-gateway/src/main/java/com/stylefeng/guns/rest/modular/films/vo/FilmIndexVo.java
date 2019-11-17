package com.stylefeng.guns.rest.modular.films.vo;

import com.stylefeng.guns.api.film.vo.BannerVo;
import com.stylefeng.guns.api.film.vo.FilmInfoVo;
import com.stylefeng.guns.api.film.vo.FilmVo;
import lombok.Data;

import java.util.List;

@Data
public class FilmIndexVo {

    public List<BannerVo> banners;
    public FilmVo hotFilms;
    public FilmVo soonFilms;
    public List<FilmInfoVo> boxRanking;
    public List<FilmInfoVo> expectRanking;
    public List<FilmInfoVo> top100;

}
