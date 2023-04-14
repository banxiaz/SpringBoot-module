package com.bai.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bai.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtil {
    private static final String SECRET = "my_SECRET"; //密钥
    private static final long EXPIRATION = 60 * 60; //过期时间，单位秒

    /**
     * 生成用户的Token，设置Token的过期时间
     * Header 和 Payload 都是 JSON 格式的数据，Signature 由 Payload、Header 和 Secret(密钥)通过特定的计算公式和加密算法得到。
     * JSON 形式的 Header 被转换成 Base64 编码，成为 JWT 的第一部分
     * Payload 也是 JSON 格式数据，其中包含了 Claims(声明，包含 JWT 的相关信息)。Payload 部分默认是不加密的，一定不要将隐私信息存放在 Payload 当中！！！JSON 形式的 Payload 被转换成 Base64 编码，成为 JWT 的第二部分。
     * 请求服务端并携带 JWT 的常见做法是将其放在 HTTP Header 的 Authorization 字段中（Authorization: Bearer Token）。
     * https://javaguide.cn/system-design/security/jwt-intro.html#payload
     *
     * @param user 用户
     * @return 计算好的token
     */
    public static String createToken(User user) {
        Date expireData = new Date(System.currentTimeMillis() + EXPIRATION * 1000); //设置过期时间
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map) // 添加头部元数据
                .withClaim("id", user.getId()) //可以将基本信息放到claim中
                .withClaim("userName", user.getUserName())
                .withClaim("password", user.getPassword())
                .withExpiresAt(expireData) //过期时间
                .withIssuedAt(new Date()) //签发时间
                .sign(Algorithm.HMAC256(SECRET)); //用私有密钥加密
        log.info("计算好了Token...[{}]", token);
        return token;
    }

    /**
     * 校验并解析Token
     *
     * @param token 需要校验的Token
     * @return 解码的信息
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = jwtVerifier.verify(token);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("token验证异常");
            return null;
        }
        return jwt.getClaims();
    }
}
