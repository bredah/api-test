package dev.bredah.client;

import dev.bredah.config.ConfigFactory;
import dev.bredah.config.EnvironmentConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseClient {
  
  protected final RequestSpecification spec;
  
  protected EnvironmentConfig environment = ConfigFactory.getEnvironment();

  protected BaseClient(){
    this.spec = new RequestSpecBuilder()
      .setBaseUri(environment.serverAddress())
      .setContentType(ContentType.JSON)
      .addFilter(new AllureRestAssured())
      .build();
    if(environment.serverPort() > 0 && environment.serverPort() != 80){
      this.spec.port(environment.serverPort());
    }
  }
}
