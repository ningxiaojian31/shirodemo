package cn.zdxh.shirodemo.shiro;

import cn.zdxh.shirodemo.entity.SysPerms;
import cn.zdxh.shirodemo.entity.SysRole;
import cn.zdxh.shirodemo.entity.SysUser;
import cn.zdxh.shirodemo.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 自定义Realm
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 授权，需要授权才会调用该方法
     * @param principalCollection
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //参数是从认证方法参数一传过来的
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        //根据用户ID去数据库查询角色和权限,并封装角色名和权限名
        List<SysPerms> sysPerms = sysUserService.findAllPermsByUserId(sysUser.getId());
        List<SysRole> sysRoles = sysUserService.findAllRolesByUserId(sysUser.getId());
        Set<String> roles = new HashSet<>();
        Set<String> perms = new HashSet<>();
        //权限
        if (sysPerms != null && sysPerms.size() > 0){
            for (SysPerms  sysPerm : sysPerms){
                //分割出每个权限
                String[] split = sysPerm.getPerms().split(",");
                perms.addAll(Arrays.asList(split));
            }
        }
        //角色
        if (sysRoles != null && sysRoles.size() > 0){
            for (SysRole  sysRole : sysRoles){
                roles.add(sysRole.getRoleName());
            }
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //设置拥有的角色
        authorizationInfo.setRoles(roles);
        //设置拥有的权限
        authorizationInfo.setStringPermissions(perms);
        return authorizationInfo;
    }

    /**
     * 登录认证，即在subject.login(token)后调用
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //login方法调用之后传过来的token
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        //根据用户名去数据库查询该用户
        List<SysUser> sysUsers = sysUserService.findUserByUsername(usernamePasswordToken.getUsername());
        SysUser sysUser = new SysUser();
        sysUser = sysUsers.get(0);//只能一个用户，其实这里用户名是唯一的

        /**
         * 参数一：传给doGetAuthorizationInfo授权作为参数的
         * 参数二：密码，交给shiro自动判断密码是否正确
         * 参数三：盐，给密码加盐后再判断
         * 参数四：自定义Realm名称
         */
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(),
                ByteSource.Util.bytes(sysUser.getSalt()),getName());
        return authenticationInfo;
    }

    /**
     * 因为了我们使用到了MD5算法，所以得告知凭证匹配器利用该算法去匹配。
     * 真正匹配的方法：assertCredentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //指定加密算法，默认只加密一次，可以选择多次加密。这里的加密要对应新建用户时加密存储的密码
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }

//    public static void main(String[] args) {
//        try {
//            String password = new Md5Hash("123456").toString();
//            Md5Hash md5Hash = new Md5Hash("e10adc3949ba59abbe56e057f20f883e","f6281f61-ae3d-4b4c-8650-d73f4bf01208");
//            System.out.println(password);//e780cbb524f6f54e2734b3114978820f
//            System.out.println(md5Hash.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }



}
