package com.droszi.api.controller;

import com.droszi.api.model.PushMessageModel;
import com.droszi.api.repository.PushMessageRepository;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import jakarta.inject.Inject;

@Path("/push")
public class PushMessageController {
  @Inject
  PushMessageRepository repository;

  @POST
  @Path("/to-user")
  @Produces(MediaType.APPLICATION_JSON)
  @ResponseStatus(201)
  public PushMessageModel sendMessage(PushMessageModel pushMessage) {
    repository.insert(pushMessage);
    return pushMessage;
  }
}
