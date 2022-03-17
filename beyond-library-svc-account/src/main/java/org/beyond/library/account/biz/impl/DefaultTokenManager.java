package org.beyond.library.account.biz.impl;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.beyond.library.account.biz.TokenManager;
import org.beyond.library.account.option.TokenOptions;
import org.beyond.library.commons.model.account.VerifyTokenResult;
import org.beyond.library.framework.common.IdFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.beyond.library.commons.constant.JwtConstants.CLAIM_APP_ENV;
import static org.beyond.library.commons.constant.JwtConstants.CLAIM_USERNAME;
import static org.beyond.library.commons.constant.JwtConstants.CLAIM_USER_ID;

/**
 * @author Beyond
 */
@Component
public class DefaultTokenManager implements TokenManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTokenManager.class);

    private final IdFactory idFactory;
    private final TokenOptions options;

    public DefaultTokenManager(final IdFactory idFactory,
                               final TokenOptions options) {
        this.idFactory = idFactory;
        this.options = options;
    }

    @Override
    public VerifyTokenResult verifyToken(final String token) {
        Jws<Claims> jws;
        try {
            jws = Jwts.parser()
                .setClock(Date::new)
                .setSigningKey(this.options.getSecretKey())
                .requireIssuer(options.getIssuer())
                .requireAudience(options.getAudience())
                .require(CLAIM_APP_ENV, this.options.getEnv())
                .parseClaimsJws(token);
        } catch (JwtException e) {
            LOGGER.debug(e.getMessage(), e);
            return VerifyTokenResult.of(false, null);
        }

        Claims body = jws.getBody();
        Date expiration = body.getExpiration();
        if (expiration != null && expiration.before(new Date())) {
            return VerifyTokenResult.of(false, null);

        }

        VerifyTokenResult.UserTokenClaims claims = new VerifyTokenResult.UserTokenClaims();
        claims.setUserId(Long.parseLong(body.get(CLAIM_USER_ID, String.class)));
        claims.setUsername(body.get(CLAIM_USERNAME, String.class));
        claims.setEnv(body.get(CLAIM_APP_ENV, String.class));
        claims.setExpiration(expiration);


        return VerifyTokenResult.of(true, claims);
    }

    @Override
    public String createToken(final long userId, final String username) {
        return Jwts.builder()
            .signWith(this.options.getAlgorithm(), this.options.getSecretKey())
            .setIssuer(options.getIssuer())
            .setAudience(options.getAudience())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + (long) options.getDuration() * 1000L))
            .setId(String.valueOf(idFactory.generate()))
            .claim(CLAIM_APP_ENV, this.options.getEnv())
            .claim(CLAIM_USER_ID, Long.toString(userId))
            .claim(CLAIM_USERNAME, username)
            .compact();
    }


}
