package FinishProject.core.fixture;

import FinishProject.core.cofig.ConfigAPI;
import FinishProject.core.serviceDB.ServiceDB;
import com.github.javafaker.Faker;
import org.aeonbits.owner.ConfigCache;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class ServiceDbResolver implements ParameterResolver, AfterAllCallback, AfterEachCallback {

    private Connection connection;
    private static ServiceDB serviceDB;
    private int companyID;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(ServiceDB.class);
    }

    @Override
    public @Nullable Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        ConfigAPI config = ConfigCache.getOrCreate(ConfigAPI.class);
        Faker faker = new Faker(Locale.ENGLISH);

        try {
            connection = DriverManager.getConnection(config.connectionURL(), config.connectionName(), config.connectionPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        serviceDB = new ServiceDB(connection);

        try {
            companyID = serviceDB.createCompany(faker.company().name(), faker.company().catchPhrase());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put("companyID", companyID);
        return serviceDB;
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        serviceDB.deleteCompany(companyID);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

}
