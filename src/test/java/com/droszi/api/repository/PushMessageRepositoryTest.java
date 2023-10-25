package com.droszi.api.repository;

import static com.droszi.api.db.public_.Tables.MESSAGES;
import static org.junit.jupiter.api.Assertions.*;

import com.droszi.api.model.PushMessageModel;
import com.droszi.api.services.JooqService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
class PushMessageRepositoryTest {
  @Inject
  JooqService jooqService;


  @BeforeEach
  void setUp() {
    jooqService.getDbContext().deleteFrom(MESSAGES).execute();
  }

  @Test
  void insert_recordGiven_recordInserted() {
    PushMessageRepository repository = new PushMessageRepository(jooqService);
    PushMessageModel pushMessage = new PushMessageModel(1, "msg", "ttl");

    repository.insert(pushMessage);

    int count = jooqService.getDbContext()
        .fetchCount(
            DSL.selectFrom(MESSAGES)
                .where(MESSAGES.USER_ID.eq(Integer.valueOf(1)))
                .and(MESSAGES.TITLE.eq("ttl"))
                .and(MESSAGES.MESSAGE.eq("msg"))
        );

    assertEquals(1, count);
  }
}
