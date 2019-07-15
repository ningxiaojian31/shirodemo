package cn.zdxh.shirodemo.controller;

import cn.zdxh.shirodemo.entity.SysUser;
import cn.zdxh.shirodemo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Api(tags = "登录相关接口")
public class LoginController {

    /**
     * 登录接口
     * @param sysUser
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("登录接口")
    public Result<String> login(SysUser sysUser){
        //这里先将密码进行MD5,实际上这个步骤是前端做的
        sysUser.setPassword(new Md5Hash(sysUser.getPassword()).toString());
        //获取到主体
        Subject subject = SecurityUtils.getSubject();
        //此对象目的是包装用户名和密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());
        //登录
        subject.login(usernamePasswordToken);
        return Result.success("登录成功");
    }

    /**
     * 退出登录接口
     * @return
     */
    @GetMapping("/logout")
    @ApiOperation("退出登录接口")
    public Result<String> logout(){
        //退出登录操作
        SecurityUtils.getSubject().logout();
        return Result.error("退出登录成功");
    }


    /**
     * 没有登录的回调接口
     * @return
     */
    @GetMapping("/loginfail")
    @ApiOperation("没有登录的回调接口")
    public Result<String> loginfail(){
        return Result.error("请登录");
    }

    /**
     * 没有权限的回调接口
     * @return
     */
    @GetMapping("/authfail")
    @ApiOperation("没有权限的回调接口")
    public Result<String> authfail(){
        return Result.error("没有操作权限");
    }

}
