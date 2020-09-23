package com.github.florianehmke.qompelo;

import com.github.florianehmke.qompelo.domain.Player;
import com.github.florianehmke.qompelo.rest.token.TokenService;
import io.restassured.specification.RequestSpecification;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import javax.transaction.UserTransaction;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class TestUtils {

  public static final EasyRandom EASY_RANDOM = easyRandom();

  private static EasyRandom easyRandom() {
    EasyRandomParameters parameters = new EasyRandomParameters();
    parameters.setObjectPoolSize(1000);
    return new EasyRandom(parameters);
  }

  public static String bearerHeader(String userName) {
    return "Bearer " + accessToken(userName);
  }

  public static String accessToken(String userName) {
    return TokenService.createToken(userName, 1000L, "compelo");
  }

  public static void doInTransaction(UserTransaction transaction, Runnable callback) {
    try {
      transaction.begin();
      callback.run();
      transaction.commit();
    } catch (Exception e) {
      System.out.println("Catastrophic Failure: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static String createTestUser(UserTransaction transaction) {
    var userName = EASY_RANDOM.nextObject(String.class);
    var passWord = EASY_RANDOM.nextObject(String.class);

    doInTransaction(transaction, () -> Player.create(userName, passWord));

    return userName;
  }

  public static RequestSpecification givenTestUser(UserTransaction transaction) {
    return givenTestUser(createTestUser(transaction));
  }

  public static RequestSpecification givenTestUser(String userName) {
    return given().header("Authorization", bearerHeader(userName)).contentType(JSON);
  }
}
