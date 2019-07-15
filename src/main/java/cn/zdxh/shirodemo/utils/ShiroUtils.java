package cn.zdxh.shirodemo.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class ShiroUtils {

    //密码加密
    public static String encryptMD5(String password){
        return new Md5Hash(password).toString();
    }

    //通过MD5加盐加密
    public static String encryptMD5(String password,String salt){
        return new Md5Hash(password,salt).toString();
    }
}
