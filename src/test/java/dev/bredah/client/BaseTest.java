package dev.bredah.client;

import java.io.File;
import com.fasterxml.jackson.core.JsonProcessingException;
import dev.bredah.helper.Schemas;
import dev.bredah.helper.Utils;
import dev.bredah.model.Authentication;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public abstract class BaseTest {

  public void validateSchema(final Response response, final Schemas schemaFile) {
    response.then().assertThat().body(JsonSchemaValidator
        .matchesJsonSchema(new File(Utils.getSchemaPath(schemaFile.toString()))));
  }

  public String getAuthToken() throws JsonProcessingException {
    Authentication auth =
        Authentication.builder().username("admin").password("password123").build();
    return new AuthenticationClient().authenticate(auth).path("token");
  }

}
