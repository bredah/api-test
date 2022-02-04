package dev.bredah.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Booking {

  @JsonProperty("firstname")
  private String firstName;

  @JsonProperty("lastname")
  private String lastName;

  @JsonProperty("totalprice")
  private Integer totalPrice;

  @JsonProperty("depositpaid")
  private Boolean depositPaid;

  @JsonProperty("bookingdates")
  private BookingDates bookingDates;

  @JsonProperty("additionalneeds")
  private String additionalNeeds;

}
