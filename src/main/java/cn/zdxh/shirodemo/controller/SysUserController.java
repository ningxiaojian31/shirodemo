package cn.zdxh.shirodemo.controller;

import cn.zdxh.shirodemo.entity.SysUser;
import cn.zdxh.shirodemo.service.SysUserService;
import cn.zdxh.shirodemo.utils.Result;
import cn.zdxh.shirodemo.utils.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户接口
 */
@Api(tags = "用户接口相关")
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询所有的用户
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询所有的用户")
    @RequiresPermissions("sys:user:list")
    public Result<String> list(){
        //此方法和注解的方式一样
        //SecurityUtils.getSubject().isPermitted("sys:user:list");
        List<SysUser> allUsers = sysUserService.findAllUsers();
        return Result.success(allUsers);
    }


    /**
     * 新增用户
     * @return
     */
    @GetMapping("/add")
    @ApiOperation("新增用户")
    @RequiresPermissions("sys:user:add")
    //@RequiresRoles("admin")
    public Result<String> add(SysUser sysUser){
        //此方法和注解的方式一样
        //SecurityUtils.getSubject().isPermitted("sys:user:list");
        try{
            //密码首先加一次密
            String password = ShiroUtils.encryptMD5(sysUser.getPassword());
            //加盐之后再进行加密
            String salt = UUID.randomUUID().toString();
            password = ShiroUtils.encryptMD5(password,salt);

            //设置并初始化值
            sysUser.setPassword(password);
            sysUser.setSalt(salt);
            sysUser.setCreateTime(new Date());
            sysUserService.addUser(sysUser);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("新增用户出现异常啦");
        }
        return Result.success("新增用户成功");
    }

}
