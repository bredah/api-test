package dev.bredah.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import dev.bredah.model.Authentication;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

class AuthenticationClientTest {

  private AuthenticationClient endpoint = new AuthenticationClient();

  @Test
  @Severity(SeverityLevel.BLOCKER)
  void testAuthenticate() throws JsonProcessingException {
    Authentication auth =
        Authentication.builder().username("admin").password("password123").build();
    Response response = endpoint.authenticate(auth);

    assertEquals(200, response.getStatusCode());
    assertNotNull(response.path("token"));
  }

  @Test
  void testInvalidAuthenticate() throws JsonProcessingException {
    Authentication auth =
        Authentication.builder().username("admin").password("password").build();
    Response response = endpoint.authenticate(auth);

    assertEquals(200, response.getStatusCode());
    assertTrue(response.getBody().asString().contains("Bad credentials"));
  }
}
