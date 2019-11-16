package com.stylefeng.guns.rest.modular.user;

import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.vo.UserModel;
import com.stylefeng.guns.rest.modular.vo.RespenseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author freedom
 * @since 2019-11-11
 */
@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserAPI userAPI;

    @RequestMapping("register")
    public RespenseVo register(UserModel userModel){
        if (userModel.getUsername()==null||userModel.getUsername().trim().length()==0){
            return RespenseVo.fail("用户名不能为空");
        }
        if (userModel.getPassword()==null||userModel.getPassword().trim().length()==0){
            return RespenseVo.fail("密码不能为空");
        }

        boolean isSuccess = userAPI.register(userModel);
        if (isSuccess){
            return RespenseVo.ok("注册成功");
        }else {
            return RespenseVo.fail("注册失败");
        }


    }
}
