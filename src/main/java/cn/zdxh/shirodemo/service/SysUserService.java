package cn.zdxh.shirodemo.service;

import cn.zdxh.shirodemo.entity.SysPerms;
import cn.zdxh.shirodemo.entity.SysRole;
import cn.zdxh.shirodemo.entity.SysUser;

import java.util.List;

public interface SysUserService {
    /**授权相关*/
    //通过用户ID查询该用户权限
    List<SysPerms> findAllPermsByUserId(Integer id);
    //通过用户ID查询该用户角色
    List<SysRole> findAllRolesByUserId(Integer id);

    /**认证相关*/
    //通过用户ID查询用户
    List<SysUser> findUserByUsername(String username);

    /**操作相关*/
    //查询所有用户
    List<SysUser> findAllUsers();
    //新增用户
    void addUser(SysUser sysUser);
}
