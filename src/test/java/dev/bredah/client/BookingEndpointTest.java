package dev.bredah.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import dev.bredah.helper.Utils;
import dev.bredah.model.Authentication;
import dev.bredah.model.Booking;
import dev.bredah.model.BookingDates;
import dev.bredah.model.BookingIdentify;
import dev.bredah.model.NewBooking;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

class BookingEndpointTest {

  private BookingEndpoint endpoint = new BookingEndpoint();

  private static Integer bookingId;

  @Test
  @Order(1)
  void testGetBooking() {
    Response response = endpoint.getBooking();
    List<BookingIdentify> bodyContent = response.getBody().as(new TypeRef<List<BookingIdentify>>() {});

    assertEquals(200, response.getStatusCode());  
    assertNotEquals(Collections.EMPTY_LIST, bodyContent);
  }

  @Test
  @Order(2)
  void testGetBookingById() {
    final Integer id = 1;
    Response response = endpoint.getBookingById(id);
    Booking bodyContent = response.getBody().as(Booking.class);
    
    assertEquals(200, response.getStatusCode());
    assertNotNull(bodyContent);
  }

  @Test
  @Order(3)
  void testCreateBooking() throws JsonProcessingException, ParseException{
    Booking expectedValue = fillBooking();

    Response response = endpoint.createBooking(expectedValue);
    NewBooking actualValue = response.getBody().as(NewBooking.class);
    
    assertEquals(200, response.getStatusCode());
    assertEquals(expectedValue, actualValue.getBooking());
    
    bookingId = actualValue.getId();
  }

  @Test
  @Order(4)
  void testUpdateBooking() throws JsonProcessingException, ParseException {
    final Booking initialValue = fillBooking();
    final String authToken = getAuthToken();
    
    Response response = endpoint.createBooking(initialValue);
    NewBooking newBooking = response.getBody().as(NewBooking.class);
    newBooking.getBooking().setBookingDates(
      new BookingDates(
        Utils.parseString("2022-01-01"),
        Utils.parseString("2022-01-02")));

    response = endpoint.updateBooking(newBooking.getBooking(), newBooking.getId(), authToken);
    Booking actualResult = response.getBody().as(Booking.class);

    assertEquals(200, response.getStatusCode());
    assertNotEquals(initialValue, actualResult);
    assertEquals(newBooking.getBooking(), actualResult);

  }

  @Test
  @Order(5)
  void testDeleteBooking() throws JsonProcessingException, ParseException {
    final Booking initialValue = fillBooking();
    final String authToken = getAuthToken();

    Response response = endpoint.createBooking(initialValue);
    NewBooking newBooking = response.getBody().as(NewBooking.class);

    response = endpoint.deleteBooking(newBooking.getId(), authToken);
    
    assertEquals(201, response.getStatusCode());
  }

  private String getAuthToken() throws JsonProcessingException{
    Authentication auth = Authentication.builder().username("admin").password("password123").build();
    return new AuthenticationEndpoint().authenticate(auth).path("token");
  }

  private Booking fillBooking() throws ParseException{
    return Booking.builder()
      .firstName("firstName")
      .lastName("lastName")
      .totalPrice(100)
      .depositPaid(true)
      .bookingDates(new BookingDates(
        Utils.parseString("2022-02-01"),
        Utils.parseString("2022-02-02")))
      .additionalNeeds("nothing")
      .build();
  }

}
