package dev.bredah.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseEndpoint {
  
  protected final RequestSpecification spec;
  
  protected String basePath;

  protected BaseEndpoint(){
    this.spec = new RequestSpecBuilder()
      .setBaseUri("https://restful-booker.herokuapp.com")
      .setContentType(ContentType.JSON)
      // .addFilter(new RequestResponseFilter())
      .build();
    // this.spec.port(8080);
  }
}
