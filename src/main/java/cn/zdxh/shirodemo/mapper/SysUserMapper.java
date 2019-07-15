package cn.zdxh.shirodemo.mapper;

import cn.zdxh.shirodemo.entity.SysPerms;
import cn.zdxh.shirodemo.entity.SysRole;
import cn.zdxh.shirodemo.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {
    /**授权相关*/
    //通过用户ID查询该用户权限
    List<SysPerms> findAllPermsByUserId(Integer id);
    //通过用户ID查询该用户角色
    List<SysRole> findAllRolesByUserId(Integer id);

    /**认证相关*/
    //通过用户名查询该用户信息
    List<SysUser> findUserByUsername(String username);

}
