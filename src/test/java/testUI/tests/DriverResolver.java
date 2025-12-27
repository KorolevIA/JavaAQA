package testUI.tests;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverResolver implements ParameterResolver, AfterEachCallback {

    private WebDriver driver;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(WebDriver.class);
    }

    @Override
    public @Nullable Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.get("https://www.saucedemo.com/");
        return driver;
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

}
