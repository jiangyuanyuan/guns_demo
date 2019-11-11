package com.stylefeng.guns.rest.modular.user.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.vo.UserInfoModel;
import com.stylefeng.guns.api.user.vo.UserModel;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.persistence.dao.MoocUserTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocUserT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = UserAPI.class)
public class UserImpl implements UserAPI {
    @Autowired
    MoocUserTMapper moocUserTMapper;



    @Override
    public int login(String username, String pwd) {
        System.out.println("user    "+username);
        MoocUserT moocUserT =new MoocUserT();
        moocUserT.setUserName(username);
        MoocUserT result = moocUserTMapper.selectOne(moocUserT);
        if (result!=null&&result.getUuid()>0){
            String md5Pwd = MD5Util.encrypt(pwd);
            if (md5Pwd.equals(result.getUserPwd())){
                return result.getUuid();
            }
        }

        return 0;
    }

    @Override
    public boolean register(UserModel userModel) {
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(userModel.getUsername());
        moocUserT.setEmail(userModel.getEmail());
        moocUserT.setAddress(userModel.getAddress());
        moocUserT.setUserPhone(userModel.getPhone());
        String md5Pwd = MD5Util.encrypt(userModel.getPassword());
        moocUserT.setUserPwd(md5Pwd);
        Integer insert = moocUserTMapper.insert(moocUserT);
        if (insert>0){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean checkUsername(String s) {
        EntityWrapper<MoocUserT> entityWrapper =  new EntityWrapper<>();
        entityWrapper.eq("user_name",s);
        Integer reslut = moocUserTMapper.selectCount(entityWrapper);
        if (reslut!=null&&reslut>0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public UserInfoModel getUserInfo(int i) {

        MoocUserT moocUserT =  moocUserTMapper.selectById(i);
        UserInfoModel userInfoModel = do2UserInfo(moocUserT);
        return userInfoModel;
    }

    private UserInfoModel do2UserInfo(MoocUserT moocUserT) {
        UserInfoModel userInfoModel =new UserInfoModel();
        userInfoModel.setUuid(moocUserT.getUuid());
        userInfoModel.setUsername(moocUserT.getUserName());
        userInfoModel.setSex(moocUserT.getUserSex());
        userInfoModel.setPhone(moocUserT.getUserPhone());
        userInfoModel.setNickname(moocUserT.getNickName());
        userInfoModel.setLifeState(moocUserT.getLifeState().toString());
        userInfoModel.setHeadAddress(moocUserT.getHeadUrl());
        userInfoModel.setEmail(moocUserT.getEmail());
        userInfoModel.setBirthday(moocUserT.getBirthday());
        userInfoModel.setBiography(moocUserT.getBiography());
        userInfoModel.setAddress(moocUserT.getAddress());
        return userInfoModel;
    }

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        MoocUserT moocUserT =new MoocUserT();
        moocUserT.setUserName(userInfoModel.getUsername());
        moocUserT.setEmail(userInfoModel.getEmail());
        moocUserT.setUserPhone(userInfoModel.getPhone());


        Integer result = moocUserTMapper.updateById(moocUserT);

        if (result!=null&result>0){
            UserInfoModel userInfoModel1 = getUserInfo(moocUserT.getUuid());
            return userInfoModel1;
        }else {
            return userInfoModel;
        }
    }
}
