package dev.bredah.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class NewBooking {
  
  @JsonProperty("bookingid")
  private int id;

  @JsonProperty("booking")
  private Booking booking;
}
