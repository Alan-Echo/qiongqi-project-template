package com.qiongqi.utils.JWT;


import com.qiongqi.config.Exception.XException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * @description: 说明
 * @author: ※狗尾巴草
 * @date: 2020-07-23 0:32
 **/
public class JWTHelp {

    private static final String subject ="博客管理系统";
    private static final String SECERT = "8677uu7fc3a3uu26a61c034d5ec824uu";

    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(SECERT);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    /**
     *
     * 解析token字符串
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 签发 token
     * @param claims
     * @param ttlMillis
     * @return  String
     * @Date	2017年11月24日
     *
     */
    public static String create(Map<String, Object> claims , long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)   // 主题
                .setIssuer(subject)     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) // 签名算法以及密匙
                .setClaims(claims);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate); // 过期时间
        }
        return builder.compact();
    }

    /**
     * 从token中根据key获取内容,如果过期则抛出异常
     *
     * @param token token
     * @return username
     */
    public static String getFromToken(String token,String key) {
        try {
            final Claims claims = getClaimsFromToken(token);
            if (claims == null) {
                return null;
            }
            Date expiration = claims.getExpiration();
            if (new Date().after(expiration)) {
                throw new XException("token 过期");
            }
            return String.valueOf(claims.get(key));
        } catch (Exception e) {
            return null;
        }
    }

    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(generalKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
        return claims;
    }


}
