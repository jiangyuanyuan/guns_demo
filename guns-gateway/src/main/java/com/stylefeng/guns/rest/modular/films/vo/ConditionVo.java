package com.stylefeng.guns.rest.modular.films.vo;

import com.stylefeng.guns.api.film.vo.CatInfoVo;
import com.stylefeng.guns.api.film.vo.SouceVo;
import com.stylefeng.guns.api.film.vo.YearVo;

import java.util.List;

public class ConditionVo {
    public List<CatInfoVo> catInfo;
    public List<SouceVo> sourceInfo;

    public List<CatInfoVo> getCatInfo() {
        return catInfo;
    }

    public void setCatInfo(List<CatInfoVo> catInfo) {
        this.catInfo = catInfo;
    }

    public List<SouceVo> getSourceInfo() {
        return sourceInfo;
    }

    public void setSourceInfo(List<SouceVo> sourceInfo) {
        this.sourceInfo = sourceInfo;
    }

    public List<YearVo> getYearInfo() {
        return yearInfo;
    }

    public void setYearInfo(List<YearVo> yearInfo) {
        this.yearInfo = yearInfo;
    }

    public List<YearVo> yearInfo;

}
