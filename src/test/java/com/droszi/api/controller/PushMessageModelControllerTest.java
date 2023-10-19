package com.droszi.api.controller;

import static com.droszi.api.db.public_.Tables.MESSAGES;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.droszi.api.model.PushMessageModel;
import com.droszi.api.services.JooqService;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.json.Json;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
@Transactional
public class PushMessageModelControllerTest {
  @Inject
  JooqService jooqService;

  @BeforeEach
  public void prepareDB() {
    jooqService.getDbContext().deleteFrom(MESSAGES).execute();
  }

  @Test
  @TestHTTPEndpoint(PushMessageController.class)
  public void testPost() {
    PushMessageModel pushMessage = new PushMessageModel(1, "msg", "ttl");
    given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(pushMessage)
    .when()
        .post("/to-user")
    .then()
        .statusCode(Status.CREATED.getStatusCode())
        .contentType(MediaType.APPLICATION_JSON)
        .body(is(Json.encode(pushMessage)));

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
