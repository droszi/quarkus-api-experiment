package com.droszi.api.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.droszi.api.model.PushMessageModel;
import com.droszi.api.repository.PushMessageRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.json.Json;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PushMessageControllerTest {
  @InjectMock
  PushMessageRepository repository;

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

    verify(repository, times(1)).insert(pushMessage);
  }
}
