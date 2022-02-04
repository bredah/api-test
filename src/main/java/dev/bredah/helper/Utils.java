package dev.bredah.helper;

import java.io.File;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

  private Utils() {}

  public static String toJson(final Object content) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(content);
  }

  public static Date parseString(final String value) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.parse(value);
  }
  
  public static String getSchemaPath(final String fileName) {
    final String path = Paths.get("src", "test", "resources", "schema").toFile().getAbsolutePath();
    return String.format("%s%s%s", path, File.separator, fileName);
  }
  
}
