package com.stylefeng.guns.rest.modular.films.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.vo.*;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Service(interfaceClass = FilmServiceAPI.class)
@Component
public class FilmsServiceImpl implements FilmServiceAPI {
    @Autowired
    MoocBannerTMapper moocBannerTMapper;


    @Autowired
    MoocFilmTMapper moocFilmTMapper;


    @Autowired
    MoocCatDictTMapper moocCatDictTMapper;

    @Autowired
    MoocSourceDictTMapper moocSourceDictTMapper;

    @Autowired
    MoocYearDictTMapper moocYearDictTMapper;


    @Override
    public List<BannerVo> getBanners() {
        List<BannerVo> bannerVoList =new ArrayList<>();
        List<MoocBannerT> bannerTList= moocBannerTMapper.selectList(null);
        for (MoocBannerT moocBannerT : bannerTList) {
            BannerVo bannerVo = new BannerVo();
            bannerVo.setBannerId(moocBannerT.getUuid().toString());
            bannerVo.setBannerAddress(moocBannerT.getBannerAddress());
            bannerVo.setBannerUrl(moocBannerT.getBannerUrl());
            bannerVoList.add(bannerVo);
        }
        return bannerVoList;
    }

    @Override
    public FilmVo getHotFilms(boolean isLimit, int num) {
        FilmVo filmVo = new FilmVo();
        List<FilmInfoVo> filmInfoVos = new ArrayList<>();
        EntityWrapper<MoocFilmT > entityWrapper = new EntityWrapper();
        entityWrapper.eq("film_status","1");

        //首页限制
        if (isLimit){
            Page<FilmInfoVo> page = new Page<>(1,num);
            List<MoocFilmT> moocFilmTList = moocFilmTMapper.selectPage(page,entityWrapper);
            filmInfoVos = getFimlsInfo(moocFilmTList);
        }else {


        }
        filmVo.setFilmInfo(filmInfoVos);


        //列表
        return filmVo;
    }

    private List<FilmInfoVo> getFimlsInfo(List<MoocFilmT> moocFilmTList) {
        List<FilmInfoVo> filmInfoVoList =new ArrayList<>();
        for (MoocFilmT moocFilmT : moocFilmTList) {
            FilmInfoVo filmInfoVo = new FilmInfoVo();
            filmInfoVo.setFilmId(moocFilmT.getUuid().toString());
            filmInfoVo.setFilmName(moocFilmT.getFilmName());
            filmInfoVo.setFilmScore(moocFilmT.getFilmScore());
            filmInfoVo.setFilmType(moocFilmT.getFilmType().toString());
            filmInfoVo.setImgAddress(moocFilmT.getImgAddress());
            filmInfoVoList.add(filmInfoVo);
        }
        return filmInfoVoList;
    }

    @Override
    public FilmVo getSoonFilms(boolean isLimit, int num) {
        FilmVo filmVo = new FilmVo();
        List<FilmInfoVo> filmInfoVos = new ArrayList<>();
        EntityWrapper<MoocFilmT > entityWrapper = new EntityWrapper();
        entityWrapper.eq("film_status","2");

        //首页限制
        if (isLimit){
            Page<FilmInfoVo> page = new Page<>(1,num);
            List<MoocFilmT> moocFilmTList = moocFilmTMapper.selectPage(page,entityWrapper);
            filmInfoVos = getFimlsInfo(moocFilmTList);
        }else {


        }
        filmVo.setFilmInfo(filmInfoVos);


        //列表
        return filmVo;
    }

    @Override
    public List<FilmInfoVo> getBoxRanking() {
        EntityWrapper<MoocFilmT > entityWrapper = new EntityWrapper();
        entityWrapper.eq("film_status","1");

        Page<MoocFilmT> page= new Page<>(1,10,"film_box_office");
        List<MoocFilmT> moocFilmTList = moocFilmTMapper.selectPage(page,entityWrapper);

        List<FilmInfoVo> filmInfoVoList = getFimlsInfo(moocFilmTList);

        return filmInfoVoList;
    }

    @Override
    public List<FilmInfoVo> getExpectRanking() {
        EntityWrapper<MoocFilmT > entityWrapper = new EntityWrapper();
        entityWrapper.eq("film_status","1");
        Page<MoocFilmT> page= new Page<>(1,10,"film_score");
        List<MoocFilmT> moocFilmTList = moocFilmTMapper.selectPage(page,entityWrapper);

        List<FilmInfoVo> filmInfoVoList = getFimlsInfo(moocFilmTList);

        return filmInfoVoList;
    }

    @Override
    public List<FilmInfoVo> getTop() {
        EntityWrapper<MoocFilmT > entityWrapper = new EntityWrapper();
        entityWrapper.eq("film_status","1");
        Page<MoocFilmT> page= new Page<>(1,10,"film_preSaleNum");
        List<MoocFilmT> moocFilmTList = moocFilmTMapper.selectPage(page,entityWrapper);

        List<FilmInfoVo> filmInfoVoList = getFimlsInfo(moocFilmTList);

        return filmInfoVoList;
    }

    @Override
    public List<CatInfoVo> getCats() {
        List<CatInfoVo> catInfoVoList =new ArrayList<>();
        List<MoocCatDictT>moocCatDictTList = moocCatDictTMapper.selectList(null);
        for (MoocCatDictT moocCatDictT : moocCatDictTList) {
            CatInfoVo catInfoVo =new CatInfoVo();
            catInfoVo.setCatId(moocCatDictT.getUuid()+"");
            catInfoVo.setCatName(moocCatDictT.getShowName());
            catInfoVoList.add(catInfoVo);
        }
        return catInfoVoList;
    }

    @Override
    public List<SouceVo> getSouces() {
        List<SouceVo> souceVoList =new ArrayList<>();
        List<MoocSourceDictT>moocSourceDictTList = moocSourceDictTMapper.selectList(null);
        for (MoocSourceDictT moocSourceDictT : moocSourceDictTList) {
            SouceVo souceVo =new SouceVo();
            souceVo.setSourceId(moocSourceDictT.getUuid()+"");
            souceVo.setSourceName(moocSourceDictT.getShowName());
            souceVoList.add(souceVo);
        }
        return souceVoList;
    }

    @Override
    public List<YearVo> getYear() {
        List<YearVo> yearVoList =new ArrayList<>();
        List<MoocYearDictT>moocYearDictTList = moocYearDictTMapper.selectList(null);
        for (MoocYearDictT moocYearDictT : moocYearDictTList) {
            YearVo yearVo =new YearVo();
            yearVo.setYearId(moocYearDictT.getUuid()+"");
            yearVo.setYearName(moocYearDictT.getShowName());
            yearVoList.add(yearVo);
        }
        return yearVoList;
    }
}
