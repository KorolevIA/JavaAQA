package FinishProject.core.fixture;

import FinishProject.core.dataProvider.DataProvider;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.IOException;

public class DataProviderResolver implements ParameterResolver {

    private DataProvider dataProvider;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(DataProvider.class);
    }

    @Override
    public @Nullable Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (dataProvider == null) {
            try {
                dataProvider = new DataProvider();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return dataProvider;
    }

}
