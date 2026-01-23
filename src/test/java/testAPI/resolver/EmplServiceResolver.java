package testAPI.resolver;

import Practice.OkHttp.AuthService;
import Practice.OkHttp.EmployeeService;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.*;

import java.io.IOException;

public class EmplServiceResolver implements ParameterResolver{

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().isAnnotationPresent(ProviderEmplService.class);
    }

    @Override
    public @Nullable Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        String login = parameterContext.getParameter().getAnnotation(ProviderEmplService.class).login();
        String password = parameterContext.getParameter().getAnnotation(ProviderEmplService.class).password();

        String token;
        try {
            token = new AuthService(login, password).getToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Object companyID = extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).get("companyID");
        return new EmployeeService(token, (Integer)companyID);
    }

}
