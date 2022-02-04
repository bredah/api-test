package dev.bredah.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import dev.bredah.model.Authentication;
import io.restassured.response.Response;

class AuthenticationEndpointTest {

  private AuthenticationEndpoint endpoint = new AuthenticationEndpoint();

  @Test
  void testAuthenticate() throws JsonProcessingException {
    Authentication auth = Authentication.builder().username("admin").password("password123").build();
    Response response = endpoint.authenticate(auth);
    
    assertNotNull(response.path("token"));
  }
}
