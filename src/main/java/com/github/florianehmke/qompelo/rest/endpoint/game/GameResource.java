package com.github.florianehmke.qompelo.rest.endpoint.game;

import com.github.florianehmke.qompelo.rest.endpoint.project.ProjectSubResource;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource extends ProjectSubResource {

  @GET
  public String create() {
    return "hello";
  }
}
