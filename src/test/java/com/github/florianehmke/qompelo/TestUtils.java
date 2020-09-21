package com.github.florianehmke.qompelo;

import com.github.florianehmke.qompelo.rest.token.TokenService;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class TestUtils {

  public static EasyRandom easyRandom() {
    EasyRandomParameters parameters = new EasyRandomParameters();
    parameters.setObjectPoolSize(10);
    return new EasyRandom(parameters);
  }

  public static String accessToken() {
    return TokenService.createToken("TEST-USER", 1000L, "compelo");
  }
}
