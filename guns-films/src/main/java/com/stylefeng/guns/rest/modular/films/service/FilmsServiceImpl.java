package com.stylefeng.guns.rest.modular.films.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.vo.BannerVo;
import com.stylefeng.guns.api.film.vo.FilmInfoVo;
import com.stylefeng.guns.api.film.vo.FilmVo;
import com.stylefeng.guns.rest.common.persistence.dao.MoocBannerTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MoocFilmTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocBannerT;
import com.stylefeng.guns.rest.common.persistence.model.MoocFilmT;
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
        filmVo.setFilmNum(filmInfoVos.size()+"");

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
    public FilmVo getSoonFilms(boolean b, int i) {
        return null;
    }

    @Override
    public List<FilmInfoVo> getBoxRanking() {
        return null;
    }

    @Override
    public List<FilmInfoVo> getExpectRanking() {
        return null;
    }

    @Override
    public List<FilmInfoVo> getTop() {
        return null;
    }
}
