package com.github.florianehmke.qompelo.testing.util;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class Random {

  public static final EasyRandom EASY_RANDOM = easyRandom();

  private static EasyRandom easyRandom() {
    EasyRandomParameters parameters = new EasyRandomParameters();
    parameters.setObjectPoolSize(1000);
    return new EasyRandom(parameters);
  }
}
