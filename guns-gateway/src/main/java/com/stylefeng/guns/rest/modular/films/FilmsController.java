package com.stylefeng.guns.rest.modular.films;


import com.stylefeng.guns.rest.modular.films.vo.BannerVo;
import com.stylefeng.guns.rest.modular.vo.RespenseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/films/")
public class FilmsController {

    @RequestMapping(value = "getIndex",method = RequestMethod.GET)
    public RespenseVo getIndex(){

        //获取banner 信息
        BannerVo bannerVo = new BannerVo();


        //获取正在热映电影


        //获取即将上映的电影


        //获取票房排行


        //获取受欢迎排行


        //获取top100



        return null;
    }
}
