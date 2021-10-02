package com.qiongqi.prj.template.utils.JWT;

import com.qiongqi.prj.template.utils.JWT.TokenResult.TokenResult;
import io.jsonwebtoken.*;

import java.util.Map;

/**
 * jwt加密和解密的工具类
 */
public class XTokenUtils {

    // 创建token
    public static String createToken(Map<String, Object> claims, long ttlMillis) throws Exception {
        String token = JWTHelp.create(claims , ttlMillis);
        return token;
    }

    // 创建刷新token
//    public String createRefreshToken(String id, String subject, long ttlMillis) throws Exception {
//        return jwtHelp.create(id+ statusCode._Joiner+statusCode.RefreshXToken,subject,ttlMillis);
//    }

    /**
     * 验证token
     * @param jwtStr
     * @return
     */
    public static TokenResult parseToken(String jwtStr) {
        TokenResult result = new TokenResult();
        Claims claims = null;
        try {
            claims = JWTHelp.parseJWT(jwtStr);
            result.success(claims);
        } catch (ExpiredJwtException e) {
            result.Expired();  //过期
        } catch (SignatureException e) {
            result.fail();
        }catch (UnsupportedJwtException | MalformedJwtException e){
            result.illegal();
        }catch (IllegalArgumentException e){
            result.fail();
        }
        return result;
    }
    // token 更新
//    public String UpdateToken(String oldJWT,String resJWT,long ttlMillis) throws Exception {
//        TokenResult result = parseToken(oldJWT);
//        if(!result.getStatus()&&result.getCode()==statusCode.JWT_ERRCODE_EXPIRE){
//            TokenResult tokenResult = parseToken(resJWT);
//            if (tokenResult.getStatus()){
//                Claims res = tokenResult.getData();
//                String resId = res.getId();
//                if(resId!=null){
//                    String rId = resId.split("_")[0];
//                    String subject = res.getSubject();
//                    return jwtHelp.create(rId,subject,ttlMillis);
//                }
//            }
//        }
//        return null;
//    }


//
//    public static void main(String[] args) throws Exception {
//        //小明失效 10s
//        String id="1209689754728701100";
//        String r_id="1209689754728701100__"+123;
//
//        XTokenUtils xTokenUtils = new XTokenUtils();
//
//        String token = xTokenUtils.createToken(id, statusCode.token_subject, 10000);
//        System.out.println("当前token："+token);
//        TokenResult result = xTokenUtils.parseToken(token);
//        System.out.println(result.getData().getId());
//
////        String sc = xTokenUtils.createToken(id,"小明", 10000);
////        String rsc = xTokenUtils.createRefreshToken(r_id,"小明", 20000);
////
////        TokenResult result = xTokenUtils.parseToken(sc);
////        TokenResult result1 = xTokenUtils.parseToken(sc+"12");
////
////        System.out.println(result.getStatus());
////        System.out.println(result.getMsg());
////        System.out.println(result.getData());
////        System.out.println(result.getData().getId());
////
////        System.out.println("\n----------------------------------------------------------\n");
////
////        System.out.println(result1.getStatus());
////        System.out.println(result1.getMsg());
////        System.out.println(result1.getData());
//
////        System.out.println("-----------------------------休眠10秒-----------------------------");
////        Thread.sleep(10001);
////
////        TokenResult result2 = xTokenUtils.parseToken(sc);
////        System.out.println(result2.getStatus());
////        System.out.println(result2.getMsg());
////        System.out.println(result2.getData());
////        String s="";
////        if(!result2.getStatus()){
////            System.out.println("------------token更新-----------");
////             s = xTokenUtils.UpdateToken(sc, rsc, 10000);
////             if (s!=null)System.out.println("更新成功");
////            TokenResult result3 = xTokenUtils.parseToken(s);
////            System.out.println(result3.getStatus());
////            System.out.println(result3.getMsg());
////            System.out.println(result3.getData());
////        }
////        System.out.println("-----------------------------休眠10秒-----------------------------");
////        Thread.sleep(10001);
////        TokenResult result4 = xTokenUtils.parseToken(s);
////        System.out.println(result4.getStatus());
////        System.out.println(result4.getMsg());
////        System.out.println(result4.getData());
//
//    }

}
