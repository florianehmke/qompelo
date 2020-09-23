package com.github.florianehmke.qompelo.testing.util;

import io.restassured.specification.RequestSpecification;

import javax.transaction.UserTransaction;

import static com.github.florianehmke.qompelo.testing.Player.createTestUser;
import static com.github.florianehmke.qompelo.testing.util.Token.accessToken;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class RestAssured {

  public static RequestSpecification givenTestUser(UserTransaction transaction) {
    return givenTestUser(createTestUser(transaction));
  }

  public static RequestSpecification givenTestUser(String userName) {
    return given().header("Authorization", bearerHeader(userName)).contentType(JSON).accept(JSON);
  }

  private static String bearerHeader(String userName) {
    return "Bearer " + accessToken(userName);
  }
}
