package cn.zdxh.shirodemo.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.shiro.codec.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Token生成/验证工具类,开源库jjwt
 * 这里暂时还没用到
 */
public class TokenUtils {
    //过期时间,30天
    private static final long TOKEN_EXPIRED_TIME = 30 * 24 * 60 * 60;

    //加密、解密秘钥明文
    private static final String JWT_SECRET = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";

    /**
     * 生成token，根据userId生成token
     */
    public static String createToken(String userId){
        //根据系统时间生成日期
        long millis = System.currentTimeMillis();
        //当前时间
        Date currentTime = new Date(millis);
        //过期时间
        Date expiretionTime = new Date(millis+TOKEN_EXPIRED_TIME);
        //荷载本体,这里还可以自定义成别的
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",userId);
        //生成token
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims) //荷载的存放
                .setIssuedAt(currentTime) //签发时间
                .setExpiration(expiretionTime) //过期时间
                .signWith(SignatureAlgorithm.HS256, generalKey()); //设置签名算法和签名秘钥
        return builder.compact();
    }

    /**
     * 验证token，根据token生成userId
     */
    public static Claims verify (String token){
        //抛出异常就表明token不正确
        //解析token,生成荷载
        Claims claims = Jwts.parser()
                .setSigningKey(generalKey()) //设置秘钥
                .parseClaimsJws(token).getBody(); //千万不要调用parseClaimsJwt方法，那个是未签名的方法
        return claims;
    }

    /**
     * 方法可选，根据秘钥明文加密生成秘钥密文，多一层保护
     */
    public static SecretKey generalKey(){
        byte[] decode = Base64.decode(JWT_SECRET); //生成byte数组
        SecretKey key = new SecretKeySpec(decode,"AES"); //根据秘钥数组和加密算法加密秘钥
        return key;
    }
}
