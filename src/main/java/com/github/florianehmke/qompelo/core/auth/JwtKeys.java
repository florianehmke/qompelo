package com.github.florianehmke.qompelo.core.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;
import java.security.PublicKey;

@Getter
@NoArgsConstructor
@AllArgsConstructor
class JwtKeys {

  private PrivateKey privateKey;
  private PublicKey publicKey;
}
