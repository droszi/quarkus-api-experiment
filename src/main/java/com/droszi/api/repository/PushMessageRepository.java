package com.droszi.api.repository;

import com.droszi.api.db.public_.tables.Messages;
import com.droszi.api.model.PushMessageModel;
import com.droszi.api.services.JooqService;

public class PushMessageRepository extends AbstractDBRepository {
  private final Messages messages = Messages.MESSAGES;

  public PushMessageRepository(JooqService jooqService) {
    super(jooqService);
  }

  public void insert(PushMessageModel pushMessage) {
    getQueryBuilder()
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
  }
}
