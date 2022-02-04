package dev.bredah.config;

import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Mutable;

@LoadPolicy(LoadType.MERGE)
@Sources({ 
  "classpath:environment.properties"
})
public interface EnvironmentConfig extends Mutable {
  
  @Key("SERVICE_ADDRESS")
  String serverAddress();

  @Key("SERVICE_PORT")
  @DefaultValue("0")
  Integer serverPort();
}
