package dev.bredah.client;

import static io.restassured.RestAssured.given;
import com.fasterxml.jackson.core.JsonProcessingException;
import dev.bredah.helper.Utils;
import dev.bredah.model.Booking;
import io.restassured.response.Response;

public class BookingEndpoint extends BaseEndpoint {

  public BookingEndpoint(){
    basePath = "/booking";
  }

  private Response get(){
    return given()
      .spec(spec)
      .when()
      .get(basePath);
  }

  private Response get(final Integer id){
    return given()
      .spec(spec)
      .when()
      .get(String.format("%s/%d", basePath, id));
  }

  private Response post(final Booking booking) throws JsonProcessingException{
    String body = Utils.toJson(booking);
    return given()
      .spec(spec)
      .and()
      .header("Content-type", "application/json")
      .and()
      .body(body)
      .when()
      .post(basePath);
  }

  private Response put(final Booking booking, final Integer id, final String token) throws JsonProcessingException{
    String body = Utils.toJson(booking);
    return given()
      .spec(spec)
      .and()
      .header("Content-type", "application/json")
      .and()
      .header("Cookie", String.format("token=%s", token))
      .and()
      .body(body)
      .when()
      .put(String.format("%s/%d", basePath, id));
  }

  private Response delete(final Integer id, final String token){
    return given()
      .spec(spec)
      .and()
      .header("Content-type", "application/json")
      .and()
      .header("Cookie", String.format("token=%s", token))
      .when()
      .delete(String.format("%s/%d", basePath, id));
  }

  public Response getBooking() {
    return get();
  }

  public Response getBookingById(final Integer id){
    return get(id);
  }

  public Response createBooking(final Booking booking) throws JsonProcessingException {
    return post(booking);
  }

  public Response updateBooking(final Booking booking, final Integer id, final String token) throws JsonProcessingException {
    return put(booking, id, token);
  }

  public Response deleteBooking(final Integer id, final String token) {
    return delete(id, token);
  }
}
