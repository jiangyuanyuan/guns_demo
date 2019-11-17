package com.stylefeng.guns.rest.modular.films;


import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.vo.BannerVo;
import com.stylefeng.guns.rest.modular.films.vo.ConditionVo;
import com.stylefeng.guns.rest.modular.films.vo.FilmIndexVo;
import com.stylefeng.guns.rest.modular.vo.RespenseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        filmIndexVo.setHotFilms(filmServiceAPI.getHotFilms(true,8));

        //获取即将上映的电影
        filmIndexVo.setSoonFilms(filmServiceAPI.getSoonFilms(true,8));


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

        //来源

        //年代

        return null;
    }
}
