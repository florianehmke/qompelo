package com.github.florianehmke.qompelo.rest.provider;

import com.github.florianehmke.qompelo.domain.Player;
import com.github.florianehmke.qompelo.rest.token.TokenData;
import org.eclipse.microprofile.jwt.Claim;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@RequestScoped
public class CurrentPlayer {

  @Inject
  @Claim(value = TokenData.Claims.USER_NAME)
  String userName;

  @Produces
  public Player currentPlayer() {
    if (userName != null) {
      return Player.mustLoad(userName);
    }
    return null;
  }
}
