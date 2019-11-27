package com.stylefeng.guns.rest.modular.vo;

public class RespenseVo<M> {
    private int status;
    private String msg;
    private M data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public M getData() {
        return data;
    }

    public void setData(M data) {
        this.data = data;
    }


    public static<M> RespenseVo ok(M m){
        RespenseVo respenseVo = new RespenseVo();
        respenseVo.setStatus(0);
        respenseVo.setMsg("ok");
        respenseVo.setData(m);
        return respenseVo;
    }


    public static<M> RespenseVo fail(String msg){
        RespenseVo respenseVo = new RespenseVo();
        respenseVo.setStatus(1);
        respenseVo.setMsg(msg);
        return respenseVo;
    }

    public static<M> RespenseVo appfail(String msg){
        RespenseVo respenseVo = new RespenseVo();
        respenseVo.setStatus(999);
        respenseVo.setMsg(msg);
        return respenseVo;
    }

}
