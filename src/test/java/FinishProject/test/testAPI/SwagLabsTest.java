package FinishProject.test.testAPI;

import FinishProject.core.cofig.ConfigUI;
import FinishProject.core.dataProvider.DataProvider;
import FinishProject.core.fixture.DataProviderResolver;
import FinishProject.core.fixture.DriverResolver;
import FinishProject.core.pages.AuthPage;
import FinishProject.core.pages.InventoryPage;
import org.aeonbits.owner.ConfigCache;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import javax.validation.constraints.Negative;
import javax.validation.constraints.Positive;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith({DriverResolver.class, DataProviderResolver.class})
public class SwagLabsTest {

    private static String URL;

    @BeforeAll
    public static void getConfig() {
        ConfigUI config = ConfigCache.getOrCreate(ConfigUI.class);
        URL = config.SwagLabsURL();
    }

    @Test
    @DisplayName("Проверка успешной авторизации")
    @Positive
    public void testAuthInSwagLabs(WebDriver driver, DataProvider data) {
        AuthPage authPage = new AuthPage(driver);
        authPage.openSwagLabs(URL);
        InventoryPage invPage = authPage.authInSwagLabs(data.getStandardLogin(), data.getPassword());

        assertEquals("https://www.saucedemo.com/inventory.html", invPage.getURL());
    }

    @Test
    @DisplayName("Проверка авторизации под заблокированной УЗ")
    @Negative
    public void testAuthInSwagLabsNegative(WebDriver driver, DataProvider data) {
        AuthPage authPage = new AuthPage(driver);
        authPage.openSwagLabs(URL);
        authPage.authInSwagLabs(data.getLockedLogin(), data.getPassword());

        assertEquals(data.getErrorMessage(), authPage.getErrorMessage());
    }

}
