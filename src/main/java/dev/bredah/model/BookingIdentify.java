package dev.bredah.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BookingIdentify {
  
  @JsonProperty("bookingid")
  private String id;
  
}
