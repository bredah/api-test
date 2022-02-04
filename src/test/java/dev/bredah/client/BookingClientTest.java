package dev.bredah.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import dev.bredah.helper.Schemas;
import dev.bredah.helper.Utils;
import dev.bredah.model.Booking;
import dev.bredah.model.BookingDates;
import dev.bredah.model.BookingIdentify;
import dev.bredah.model.NewBooking;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

class BookingClientTest extends BaseTest {

  private BookingClient endpoint = new BookingClient();

  @Test
  void testGetBooking() {
    Response response = endpoint.getBooking();
    List<BookingIdentify> bodyContent =
        response.getBody().as(new TypeRef<List<BookingIdentify>>() {});

    validateSchema(response, Schemas.BOOKING_ID);
    assertEquals(200, response.getStatusCode());
    assertNotEquals(Collections.EMPTY_LIST, bodyContent);
  }

  @Test
  void testGetBookingById() {
    Response response = endpoint.getBooking();
    List<BookingIdentify> bookingList =
        response.getBody().as(new TypeRef<List<BookingIdentify>>() {});

    final Integer id = Integer.parseInt(bookingList.get(0).getId());
    response = endpoint.getBookingById(id);
    Booking bodyContent = response.getBody().as(Booking.class);

    validateSchema(response, Schemas.BOOKING);
    assertEquals(200, response.getStatusCode());
    assertNotNull(bodyContent);
  }

  @Test
  void testGetBookingById_IdNotExists() {
    Response response = endpoint.getBookingById(790);
    assertEquals(404, response.getStatusCode());
  }

  @Test
  void testCreateBooking() throws JsonProcessingException, ParseException {
    Booking expectedValue = fillBooking();

    Response response = endpoint.createBooking(expectedValue);
    NewBooking actualValue = response.getBody().as(NewBooking.class);

    validateSchema(response, Schemas.BOOKING_CREATE);
    assertEquals(200, response.getStatusCode());
    assertEquals(expectedValue, actualValue.getBooking());
  }

  @Test
  void testUpdateBooking() throws JsonProcessingException, ParseException {
    final Booking initialValue = fillBooking();
    final String authToken = getAuthToken();

    Response response = createBooking(initialValue);

    NewBooking newBooking = response.getBody().as(NewBooking.class);
    newBooking.getBooking().setBookingDates(
        new BookingDates(Utils.parseString("2022-01-01"), Utils.parseString("2022-01-02")));

    response = endpoint.updateBooking(newBooking.getBooking(), newBooking.getId(), authToken);
    Booking actualResult = response.getBody().as(Booking.class);

    validateSchema(response, Schemas.BOOKING);
    assertEquals(200, response.getStatusCode());
    assertNotEquals(initialValue, actualResult);
    assertEquals(newBooking.getBooking(), actualResult);
  }

  @Test
  void testUpdateBooking_InvalidToken() throws JsonProcessingException, ParseException {
    final Booking initialValue = fillBooking();
    final String authToken = "invalid_token";

    Response response = createBooking(initialValue);
    NewBooking newBooking = response.getBody().as(NewBooking.class);
    newBooking.getBooking().setBookingDates(
        new BookingDates(Utils.parseString("2022-01-01"), Utils.parseString("2022-01-02")));

    response = endpoint.updateBooking(newBooking.getBooking(), newBooking.getId(), authToken);

    assertEquals(403, response.getStatusCode());
  }

  @Test
  void testDeleteBooking() throws JsonProcessingException, ParseException {
    final Booking initialValue = fillBooking();
    final String authToken = getAuthToken();

    Response response = createBooking(initialValue);
    NewBooking newBooking = response.getBody().as(NewBooking.class);

    response = endpoint.deleteBooking(newBooking.getId(), authToken);

    assertEquals(201, response.getStatusCode());
  }

  @Test
  void testDeleteBooking_InvalidToken() throws JsonProcessingException, ParseException {
    final Booking initialValue = fillBooking();
    final String authToken = "invalid_token";

    Response response = endpoint.createBooking(initialValue);
    NewBooking newBooking = response.getBody().as(NewBooking.class);

    response = endpoint.deleteBooking(newBooking.getId(), authToken);

    assertEquals(403, response.getStatusCode());
  }

  @Test
  void testDeleteBooking_IdNotExists() throws JsonProcessingException, ParseException {
    final String authToken = getAuthToken();

    Response response = endpoint.deleteBooking(890, authToken);

    assertEquals(405, response.getStatusCode());
  }

  @Step("Create a new booking: {booking}")
  private Response createBooking(Booking booking) throws JsonProcessingException {
    Response response = endpoint.createBooking(booking);
    assertEquals(200, response.getStatusCode());
    assertTrue(response.contentType().contains("json"),
        String.format("unexpected content-type, expected: %s - received: %s", "application/json",
            response.getContentType()));
    return response;
  }

  
  private Booking fillBooking() throws ParseException {
    return Booking.builder().firstName("firstName").lastName("lastName").totalPrice(100)
        .depositPaid(true)
        .bookingDates(
            new BookingDates(Utils.parseString("2022-02-01"), Utils.parseString("2022-02-02")))
        .additionalNeeds("nothing").build();
  }

}
