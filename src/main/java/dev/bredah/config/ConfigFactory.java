package dev.bredah.config;

import org.aeonbits.owner.ConfigCache;

public final class ConfigFactory {
  
  private ConfigFactory(){}

  public static EnvironmentConfig getEnvironment(){
    return ConfigCache.getOrCreate(
      EnvironmentConfig.class,
      System.getProperties(),
      System.getenv());
  }
}
