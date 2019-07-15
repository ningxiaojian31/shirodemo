package cn.zdxh.shirodemo.service.impl;

import cn.zdxh.shirodemo.entity.SysPerms;
import cn.zdxh.shirodemo.entity.SysRole;
import cn.zdxh.shirodemo.entity.SysUser;
import cn.zdxh.shirodemo.mapper.SysUserMapper;
import cn.zdxh.shirodemo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysPerms> findAllPermsByUserId(Integer id) {
        return sysUserMapper.findAllPermsByUserId(id);
    }

    @Override
    public List<SysRole> findAllRolesByUserId(Integer id) {
        return sysUserMapper.findAllRolesByUserId(id);
    }

    @Override
    public List<SysUser> findUserByUsername(String username) {
        return sysUserMapper.findUserByUsername(username);
    }

    @Override
    public List<SysUser> findAllUsers() {
        return sysUserMapper.selectList(null);
    }

    @Override
    public void addUser(SysUser sysUser) {
        sysUserMapper.insert(sysUser);
    }
}
