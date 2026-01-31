package FinishProject.core.cofig;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:test.properties"})
public interface ConfigUI extends Config {

    @Key("SwagLabs.url")
    String SwagLabsURL();
}
