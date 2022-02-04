package dev.bredah.helper;

public enum Schemas {
  BOOKING("booking.schema.json"),
  BOOKING_CREATE("booking-create.schema.json"),
  BOOKING_ID("booking-id.schema.json");

  private final String schema;

  Schemas(final String schema){
    this.schema = schema;
  }

  @Override
  public String toString(){
    return this.schema;
  }
}
