package testAPI.resolver;

import Practice.ServiceDB.ServiceDB;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.*;

import java.sql.SQLException;

public class ServiceDBResolver implements ParameterResolver, AfterEachCallback, AfterAllCallback {

    private ServiceDB serviceDB;
    private int companyID;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().isAnnotationPresent(ProviderDB.class);
    }

    @Override
    public @Nullable Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        String CONNECTION_URL = parameterContext.getParameter().getAnnotation(ProviderDB.class).URL();
        String CONNECTION_USERNAME = parameterContext.getParameter().getAnnotation(ProviderDB.class).USERNAME();
        String CONNECTION_PASSWORD = parameterContext.getParameter().getAnnotation(ProviderDB.class).PASSWORD();

        try {
            serviceDB = new ServiceDB(CONNECTION_URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
            companyID = serviceDB.createCompany("Test", "For test");
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
        if (serviceDB != null) {
            serviceDB.closeConnection();
        }
    }

}
