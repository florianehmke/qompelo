package com.github.florianehmke.qompelo.core.auth;

import com.github.florianehmke.qompelo.core.auth.configuration.JwtConfiguration;
import com.github.florianehmke.qompelo.core.auth.models.TokenData;
import io.jsonwebtoken.Jwts;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.util.Date;

import static com.github.florianehmke.qompelo.core.auth.models.TokenData.Claims.PROJECT_ID;

@ApplicationScoped
class AuthService {

  @Inject JwtConfiguration jwtConfiguration;
  @Inject JwtKeys jwtKeys;

  String createToken(TokenData tokenData) {
    var issuedAt = Instant.now();
    var expiresAt = issuedAt.plusSeconds(jwtConfiguration.getMaxAge());

    return Jwts.builder()
        .setIssuer(jwtConfiguration.getIssuer())
        .setIssuedAt(Date.from(issuedAt))
        .setExpiration(Date.from(expiresAt))
        .addClaims(tokenData.claims())
        .signWith(jwtKeys.getPrivateKey())
        .compact();
  }

  TokenData parse(String token) {
    var parsed =
        Jwts.parser().setSigningKey(jwtKeys.getPublicKey()).parseClaimsJws(token).getBody();

    return TokenData.builder().projectId(parsed.get(PROJECT_ID, Long.class)).build();
  }
}
