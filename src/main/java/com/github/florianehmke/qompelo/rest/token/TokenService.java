package com.github.florianehmke.qompelo.rest.token;

import com.github.florianehmke.qompelo.rest.security.Roles;
import io.smallrye.jwt.build.Jwt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.util.Set;

import static com.github.florianehmke.qompelo.rest.token.TokenData.Claims.PROJECTS;
import static com.github.florianehmke.qompelo.rest.token.TokenData.Claims.USER_NAME;

@ApplicationScoped
public class TokenService {

  @Inject TokenConfiguration tokenConfiguration;

  public String createToken(TokenData tokenData) {
    var issuedAt = Instant.now();
    var expiresAt = issuedAt.plusSeconds(tokenConfiguration.getMaxAge());

    return Jwt.issuer(tokenConfiguration.getIssuer())
        .groups(Set.of(Roles.AUTHENTICATED))
        .subject(tokenData.getUserName())
        .preferredUserName(tokenData.getUserName())
        .claim(USER_NAME, tokenData.getUserName())
        .claim(PROJECTS, tokenData.getProjects())
        .expiresAt(expiresAt)
        .issuedAt(issuedAt)
        .sign();
  }
}
