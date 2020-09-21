package com.github.florianehmke.qompelo.rest.token;

import com.github.florianehmke.qompelo.rest.security.Roles;
import io.smallrye.jwt.build.Jwt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.util.Set;

@ApplicationScoped
public class TokenService {

  @Inject TokenConfiguration tokenConfiguration;

  public String createToken(String userName) {
    return createToken(userName, tokenConfiguration.getMaxAge(), tokenConfiguration.getIssuer());
  }

  public static String createToken(String userName, Long maxAge, String issuer) {
    var issuedAt = Instant.now();
    var expiresAt = issuedAt.plusSeconds(maxAge);

    return Jwt.issuer(issuer)
        .groups(Set.of(Roles.AUTHENTICATED))
        .subject(userName)
        .preferredUserName(userName)
        .expiresAt(expiresAt)
        .issuedAt(issuedAt)
        .sign();
  }
}
