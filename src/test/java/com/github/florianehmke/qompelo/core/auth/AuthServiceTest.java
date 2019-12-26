package com.github.florianehmke.qompelo.core.auth;

import com.github.florianehmke.qompelo.core.auth.models.TokenData;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class AuthServiceTest {

  @Inject private AuthService service;

  @Test
  void token() {
    var tokenData = TokenData.builder().projectId(42L).build();
    var accessToken = service.createToken(tokenData);
    var parsedTokenData = service.parse(accessToken);
    assertThat(parsedTokenData).isEqualToComparingFieldByField(tokenData);
  }
}
