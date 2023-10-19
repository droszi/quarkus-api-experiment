package com.droszi.api.controller;

import com.droszi.api.db.public_.tables.Messages;
import com.droszi.api.model.PushMessageModel;
import com.droszi.api.services.JooqService;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import jakarta.inject.Inject;

@Path("/push")
public class PushMessageController {
  @Inject
  JooqService jooqService;

  @POST
  @Path("/to-user")
  @Produces(MediaType.APPLICATION_JSON)
  @ResponseStatus(201)
  public PushMessageModel sendMessage(PushMessageModel pushMessage) {

    Messages messages = Messages.MESSAGES;

    jooqService.getDbContext()
        .insertInto(
            messages,
            messages.USER_ID,
            messages.TITLE,
            messages.MESSAGE
        )
        .values(
            pushMessage.userId(),
            pushMessage.title(),
            pushMessage.message()
        )
        .execute();

    return pushMessage;
  }
}
