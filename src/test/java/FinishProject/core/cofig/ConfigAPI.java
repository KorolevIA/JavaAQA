package FinishProject.core.cofig;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:test.properties"})
public interface ConfigAPI extends Config {

    @Key("auth.url")
    String authURL();

    @Key("company.url")
    String companyURL();

    @Key("employee.url")
    String employeeURL();

    @Key("connection.url")
    String connectionURL();

    @Key("connection.name")
    String connectionName();

    @Key("connection.password")
    String connectionPassword();

}
