package com.stylefeng.guns.rest.modular.films;


import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.vo.*;
import com.stylefeng.guns.rest.modular.films.vo.ConditionVo;
import com.stylefeng.guns.rest.modular.films.vo.FilmIndexVo;
import com.stylefeng.guns.rest.modular.films.vo.FilmReqDto;
import com.stylefeng.guns.rest.modular.vo.RespenseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/films/")
public class FilmsController {
    @Reference(interfaceClass = FilmServiceAPI.class)
    FilmServiceAPI filmServiceAPI;


    @RequestMapping(value = "getIndex",method = RequestMethod.GET)
    public RespenseVo getIndex(){
        FilmIndexVo filmIndexVo =new FilmIndexVo();
        //获取banner 信息
        filmIndexVo.setBanners(filmServiceAPI.getBanners());

        //获取正在热映电影
        filmIndexVo.setHotFilms(filmServiceAPI.getHotFilms(true,8,1,"1","1","1","1"));

        //获取即将上映的电影
        filmIndexVo.setSoonFilms(filmServiceAPI.getSoonFilms(true,8,1,"1","1","1","1"));


        //获取票房排行
        filmIndexVo.setBoxRanking(filmServiceAPI.getBoxRanking());

        //获取受欢迎排行
        filmIndexVo.setExpectRanking(filmServiceAPI.getExpectRanking());

        //获取top100
        filmIndexVo.setTop100(filmServiceAPI.getTop());

        return RespenseVo.ok(filmIndexVo);
    }

    @RequestMapping(value = "getConditionList",method = RequestMethod.POST)
    public RespenseVo getConditionList(@RequestParam(name = "catId",required = false,defaultValue = "99") String catId,
                                       @RequestParam(name = "sourceId",required = false,defaultValue = "99") String sourceId,
                                       @RequestParam(name = "yearId",required = false,defaultValue = "99") String yearId
                                       ){

        ConditionVo conditionVo = new ConditionVo();
        //类型
        List<CatInfoVo> catInfoVoList = filmServiceAPI.getCats();
        for (CatInfoVo catInfoVo : catInfoVoList) {
            catInfoVo.setActive(catInfoVo.getCatId().equals(catId));
        }
        conditionVo.setCatInfo(catInfoVoList);
        //来源
        List<SouceVo> soucesVoList = filmServiceAPI.getSouces();
        for (SouceVo souceVo : soucesVoList) {
            souceVo.setActive(souceVo.getSourceId().equals(sourceId));
        }
        conditionVo.setSourceInfo(soucesVoList);
        //年代
        List<YearVo> yearVoList = filmServiceAPI.getYear();
        for (YearVo yearVo : yearVoList) {
            yearVo.setActive(yearVo.getYearId().equals(yearId));
        }
        conditionVo.setYearInfo(yearVoList);
        return RespenseVo.ok(conditionVo);
    }


//    {
//        status: 0,
//            imgPre：’http://img.meetingshop.cn/’,
//            nowPage : 1,
//            totalPage : 3,
//            data: [
//        {
//            filmId：’001’,
//            filmType：1, [0-2D,1-3D,2-3DIMAX,4-无],
//            imgAddress：‘img/film/001.jpg’,
//            filmName：‘我不是药神’,
//            filmScore：‘8.3’
//        },
//        {
//            filmId：’002’,
//            filmType：4, [0-2D,1-3D,2-3DIMAX,4-无],
//            imgAddress：‘img/film/002.jpg’,
//            filmName：‘摩天营救’,
//            filmScore：‘9.0’
//        }
//]
//
//    }

    @RequestMapping(value = "getFilms",method = RequestMethod.POST)
    public RespenseVo getFilms(FilmReqDto filmReqDto){
        //根据showType查询

        //根据sortId查询

        //添加各种条件查询

        //判断当前是第几页



        return null;
    }

    //影片详情接口
    @RequestMapping(value = "getFilms/{searchParam}",method = RequestMethod.GET)
    public RespenseVo films(@PathVariable("searchParam") String param,int searchType){

        //searchType   查询条件不同
        FilmDetailVo filmDetailVo = filmServiceAPI.getFilmDetail(searchType,param);
        String filmId = filmDetailVo.getFilmId();

        FilmDescVo filmDesc = filmServiceAPI.getFilmDesc(filmId);

        ActorVo dectInfo = filmServiceAPI.getDectInfo(filmId);

        ImgVo imgs = filmServiceAPI.getImgs(filmId);

        List<ActorVo> actors = filmServiceAPI.getActors(filmId);

        InfoReqVo infoReqVo = new InfoReqVo();
        ActorReqVo actorReqVo =new ActorReqVo();
        actorReqVo.setActors(actors);
        actorReqVo.setDirector(dectInfo);

        infoReqVo.setActors(actorReqVo);
        infoReqVo.setImgVo(imgs);
        infoReqVo.setBiography(filmDesc.getBiography());
        infoReqVo.setFilmId(filmId);
        filmDetailVo.setInfo04(infoReqVo);
        return RespenseVo.ok(filmDetailVo);
    }


}
