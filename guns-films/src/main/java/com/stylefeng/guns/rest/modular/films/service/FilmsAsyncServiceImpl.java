package com.stylefeng.guns.rest.modular.films.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.film.FilmAsyncServiceAPI;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.vo.*;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = FilmAsyncServiceAPI.class)
@Component
public class FilmsAsyncServiceImpl implements FilmAsyncServiceAPI {


    @Autowired
    MoocFilmInfoTMapper moocFilmInfoTMapper;


    @Autowired
    MoocActorTMapper moocActorTMapper;



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
