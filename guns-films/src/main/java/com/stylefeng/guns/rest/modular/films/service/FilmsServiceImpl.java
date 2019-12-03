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

    @Autowired
    MoocFilmInfoTMapper moocFilmInfoTMapper;


    @Autowired
    MoocActorTMapper moocActorTMapper;


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
    public FilmVo getHotFilms(boolean isLimit,int nowpage,int num,String sortId,String sourceId,String yearId,String catId) {
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
            Page<MoocFilmT> page =new Page<>(nowpage,num);
            if (sourceId!="99"){
                entityWrapper.eq("film_source",sourceId);
            }
            if (yearId!="99"){
                entityWrapper.eq("film_data",yearId);
            }
            if (catId!="99"){
                String catStr = "%#"+catId+"%#";
                entityWrapper.like("film_cats",catStr);
            }
            List<MoocFilmT> moocFilmTList = moocFilmTMapper.selectPage(page,entityWrapper);
            filmInfoVos = getFimlsInfo(moocFilmTList);


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
    public FilmVo getSoonFilms(boolean isLimit,int nowpage,int num,String sortId,String sourceId,String yearId,String catId) {
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
            Page<MoocFilmT> page =new Page<>(nowpage,num);
            if (sourceId!="99"){
                entityWrapper.eq("film_source",sourceId);
            }
            if (yearId!="99"){
                entityWrapper.eq("film_data",yearId);
            }
            if (catId!="99"){
                String catStr = "%#"+catId+"%#";
                entityWrapper.like("film_cats",catStr);
            }
            List<MoocFilmT> moocFilmTList = moocFilmTMapper.selectPage(page,entityWrapper);
            filmInfoVos = getFimlsInfo(moocFilmTList);

        }
        filmVo.setFilmInfo(filmInfoVos);


        //列表
        return filmVo;
    }

    @Override
    public FilmVo getClassicFilms(boolean isLimit,int nowpage,int num,String sortId,String sourceId,String yearId,String catId) {
        FilmVo filmVo = new FilmVo();
        List<FilmInfoVo> filmInfoVos = new ArrayList<>();
        EntityWrapper<MoocFilmT > entityWrapper = new EntityWrapper();
        entityWrapper.eq("film_status","3");

        Page<MoocFilmT> page =new Page<>(nowpage,num);
        if (sourceId!="99"){
            entityWrapper.eq("film_source",sourceId);
        }
        if (yearId!="99"){
            entityWrapper.eq("film_data",yearId);
        }
        if (catId!="99"){
            String catStr = "%#"+catId+"%#";
            entityWrapper.like("film_cats",catStr);
        }
        List<MoocFilmT> moocFilmTList = moocFilmTMapper.selectPage(page,entityWrapper);
        filmInfoVos = getFimlsInfo(moocFilmTList);
        filmVo.setFilmInfo(filmInfoVos);

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

    @Override
    public FilmDetailVo getFilmDetail(int seachType, String searchParam) {
        //1 按ID 查   按用户名查
        FilmDetailVo filmDetailVo =null;

        if (seachType==1){
            filmDetailVo = moocFilmTMapper.getFilmDetailByName("%"+searchParam+"%");
        }else {
            filmDetailVo = moocFilmTMapper.getFilmDetailById(searchParam);
        }

        return filmDetailVo;
    }

    @Override
    public FilmDescVo getFilmDesc(String s) {
        MoocFilmInfoT moocFilmInfoT =  getFilmInfo(s);
        FilmDescVo filmDescVo = new FilmDescVo();
        filmDescVo.setBiography(moocFilmInfoT.getBiography());
        filmDescVo.setFilmId(moocFilmInfoT.getFilmId());
        return filmDescVo;
    }

    @Override
    public ImgVo getImgs(String s) {
        MoocFilmInfoT moocFilmInfoT =  getFilmInfo(s);
        ImgVo imgVo = new ImgVo();
        String imgs = moocFilmInfoT.getFilmImgs();
        String[] filmImgs = imgs.split(",");
        imgVo.setMainImg(filmImgs[0]);
        imgVo.setImg01(filmImgs[1]);
        imgVo.setImg02(filmImgs[2]);
        imgVo.setImg03(filmImgs[3]);
        imgVo.setImg04(filmImgs[3]);
        return imgVo;
    }

    @Override
    public ActorVo getDectInfo(String s) {
        MoocFilmInfoT moocFilmInfoT =  getFilmInfo(s);
        Integer dectId =moocFilmInfoT.getDirectorId();
        MoocActorT moocActorT = moocActorTMapper.selectById(dectId);

        ActorVo actorVo =  new ActorVo();
        actorVo.setDirectorName(moocActorT.getActorName());
        actorVo.setImgAddress(moocActorT.getActorImg());
        actorVo.setRoleName(moocActorT.getActorName());
        return actorVo;
    }

    @Override
    public List<ActorVo> getActors(String s) {
        List<ActorVo> actors = moocActorTMapper.getActors(s);
        return actors;
    }

    public MoocFilmInfoT getFilmInfo(String filmId){
        MoocFilmInfoT moocFilmInfoT =new MoocFilmInfoT();
        moocFilmInfoT.setFilmId(filmId);
        moocFilmInfoT = moocFilmInfoTMapper.selectOne(moocFilmInfoT);
        return moocFilmInfoT;
    }

}
