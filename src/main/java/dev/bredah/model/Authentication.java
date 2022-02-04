package dev.bredah.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Authentication {

  @JsonProperty("username")
  private String username;

  @JsonProperty("password")
  private String password;

}
