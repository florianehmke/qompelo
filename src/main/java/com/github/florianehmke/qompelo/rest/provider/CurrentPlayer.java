package com.github.florianehmke.qompelo.rest.provider;

import com.github.florianehmke.qompelo.domain.player.Player;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@RequestScoped
public class CurrentPlayer {

  @Inject
  @Claim(standard = Claims.preferred_username)
  String userName;

  @Produces
  public Player currentPlayer() {
    if (userName != null) {
      return Player.mustLoad(userName);
    }
    return null;
  }
}
