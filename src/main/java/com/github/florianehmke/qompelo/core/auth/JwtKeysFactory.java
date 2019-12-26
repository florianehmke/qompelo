package com.github.florianehmke.qompelo.core.auth;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static java.lang.Thread.currentThread;
import static java.nio.file.Files.readAllBytes;
import static java.util.Objects.requireNonNull;

class JwtKeysFactory {

  @Produces
  @ApplicationScoped
  JwtKeys createAlgorithm() {
    try {
      var privateKeyContent = mustReadResourceAsString("META-INF/resources/privateKey.pem");
      var publicKeyContent = mustReadResourceAsString("META-INF/resources/publicKey.pem");

      var keyFactory = KeyFactory.getInstance("RSA");
      var base64Decoder = Base64.getDecoder();

      var keySpecPKCS8 = new PKCS8EncodedKeySpec(base64Decoder.decode(privateKeyContent));
      var privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpecPKCS8);

      var keySpecX509 = new X509EncodedKeySpec(base64Decoder.decode(publicKeyContent));
      var publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpecX509);

      return new JwtKeys(privateKey, publicKey);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static String mustReadResourceAsString(String path) {
    try {
      var resource = requireNonNull(currentThread().getContextClassLoader().getResource(path));
      var resourceBytes = readAllBytes(Paths.get(resource.toURI()));
      return stripComments(new String(resourceBytes));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static String stripComments(String key) {
    return requireNonNull(key)
        .replaceAll("\\n", "")
        .replace("-----BEGIN PRIVATE KEY-----", "")
        .replace("-----END PRIVATE KEY-----", "")
        .replace("-----BEGIN PUBLIC KEY-----", "")
        .replace("-----END PUBLIC KEY-----", "");
  }
}
