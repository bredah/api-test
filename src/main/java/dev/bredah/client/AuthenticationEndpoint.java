package dev.bredah.client;

import static io.restassured.RestAssured.given;
import com.fasterxml.jackson.core.JsonProcessingException;
import dev.bredah.helper.Utils;
import dev.bredah.model.Authentication;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthenticationEndpoint extends BaseEndpoint {

  public AuthenticationEndpoint(){
    basePath = "/auth";
  }

    private Response post(Authentication auth) throws JsonProcessingException{
      String body = Utils.toJson(auth);
      return given()
        .spec(spec)
        .and()
        .header("Content-type", "application/json")
        .and()
        .body(body)
        .when()
        .post(basePath)
        .then().log().ifValidationFails()
        .statusCode(200)      
        .contentType(ContentType.JSON)
        .extract().response();
  }

  public Response authenticate(Authentication auth) throws JsonProcessingException{
    return post(auth);
  }
  
}
