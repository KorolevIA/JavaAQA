package FinishProject.core.fixture;

import FinishProject.core.cofig.ConfigAPI;
import FinishProject.core.dataProvider.DataProvider;
import FinishProject.core.serviceAPI.AuthService;
import FinishProject.core.serviceAPI.CompanyService;
import FinishProject.core.serviceAPI.EmployeeService;
import org.aeonbits.owner.ConfigCache;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.IOException;

public class EmployeeServiceResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(EmployeeService.class);
    }

    @Override
    public @Nullable Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        ConfigAPI config = ConfigCache.getOrCreate(ConfigAPI.class);

        DataProvider data;
        String token;
        int companyID;

        try {
            data = new DataProvider();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            token = new AuthService(config.authURL()).getToken(data.getLoginAPI(), data.getPasswordAPI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            companyID = new CompanyService(config.companyURL(), token).createCompany("Test", "For test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new EmployeeService(config.employeeURL(), token, companyID);
    }

}
