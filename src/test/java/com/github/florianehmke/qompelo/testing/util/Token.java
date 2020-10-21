package com.github.florianehmke.qompelo.testing.util;

import com.github.florianehmke.qompelo.rest.token.TokenService;

public class Token {

  public static String accessToken(String userName) {
    return TokenService.createToken(userName, 1000L, "compelo");
  }
}
