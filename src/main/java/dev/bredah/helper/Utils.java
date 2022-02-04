package dev.bredah.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

  private  Utils(){}

  public static String toJson(Object content) throws JsonProcessingException{
    return new ObjectMapper().writeValueAsString(content);
  }
  
  public static Date parseString(String value) throws ParseException{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.parse(value);
  }
}
