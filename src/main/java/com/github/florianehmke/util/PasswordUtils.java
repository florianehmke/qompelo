package com.github.florianehmke.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtils {

  public static String hashPassword(String password) {
    return BCrypt.withDefaults().hashToString(12, password.toCharArray());
  }

  public static boolean verifyPassword(String password, String hash) {
    var result = BCrypt.verifyer().verify(password.toCharArray(), hash);
    return result.verified;
  }
}
