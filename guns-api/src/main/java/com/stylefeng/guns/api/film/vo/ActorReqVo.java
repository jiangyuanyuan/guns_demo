package com.stylefeng.guns.api.film.vo;

import java.io.Serializable;
import java.util.List;

public class ActorReqVo implements Serializable {
    public ActorVo getDirector() {
        return director;
    }

    public void setDirector(ActorVo director) {
        this.director = director;
    }

    public List<ActorVo> getActors() {
        return actors;
    }

    public void setActors(List<ActorVo> actors) {
        this.actors = actors;
    }

    private ActorVo director;
    private List<ActorVo> actors;

}
