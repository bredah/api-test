package dev.bredah.helper;

import java.util.logging.Level;
import java.util.logging.Logger;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RequestResponseFilter implements Filter{

  private static final Logger logger = Logger.getLogger(RequestResponseFilter.class.getName());

  @Override
  public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
    Response response = ctx.next(requestSpec, responseSpec);

    if(response.statusCode() != 200){
      logger.log(Level.INFO, String.format("endpoint: %s%s - status code: %d", requestSpec.getBaseUri(), requestSpec.getBasePath(), response.statusCode()));
    }else{
      logger.log(Level.INFO, String.format("endpoint: %s%s - status code: %d", requestSpec.getBaseUri(), requestSpec.getBasePath(), response.statusCode()));
    }
    return response;
  }
  
  

}
