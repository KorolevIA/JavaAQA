package testAPI.resolver;

import Practice.OkHttp.AuthService;
import Practice.OkHttp.CompanyService;
import Practice.OkHttp.EmployeeService;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.*;

import java.io.IOException;

public class EmplServiceResolver implements ParameterResolver, AfterEachCallback {

    private int companyID;
    private CompanyService companyService;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().isAnnotationPresent(EmplService.class);
    }

    @Override
    public @Nullable Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        String login = parameterContext.getParameter().getAnnotation(EmplService.class).login();
        String password = parameterContext.getParameter().getAnnotation(EmplService.class).password();

        String token;
        try {
            token = new AuthService(login, password).getToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        companyService = new CompanyService(token);
        try {
            companyID = companyService.addCompany();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new EmployeeService(token, companyID);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        companyService.deleteCompany(companyID);
    }

}
