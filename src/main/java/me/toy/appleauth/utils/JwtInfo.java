package me.toy.appleauth.utils;

import com.auth0.jwt.algorithms.Algorithm;

/**
 * Created by dongchul on 2019-09-10.
 */
public abstract class JwtInfo {
    public static final String HEADER_NAME = "jwt-header";
    public static final String ISSUER = "dongchul";
    public static final String TOKEN_KEY = "sah3122.github.io";
    public static final long EXPIRES_LIMIT = 3L;
    public static final long REFRESH_EXPIRES_LIMIT = 30L;

    public static Algorithm getAlgorithm() {
        try {
            return Algorithm.HMAC256(JwtInfo.TOKEN_KEY);
        } catch (IllegalArgumentException e) {
            return Algorithm.none();
        }
    }


}
