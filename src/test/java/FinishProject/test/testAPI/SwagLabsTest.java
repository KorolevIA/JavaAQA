package FinishProject.test.testAPI;

import FinishProject.core.dataProvider.DataProvider;
import FinishProject.core.fixture.DataProviderResolver;
import FinishProject.core.fixture.DriverResolver;
import FinishProject.core.pages.AuthPage;
import FinishProject.core.pages.InventoryPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith({DriverResolver.class, DataProviderResolver.class})
public class SwagLabsTest {

    @Test
    public void testAuthInSwagLabs(WebDriver driver, DataProvider data) {
        AuthPage authPage = new AuthPage(driver);
        authPage.openSwagLabs(data.getURL());
        InventoryPage invPage = authPage.authInSwagLabs(data.getLogin(), data.getPassword());

        assertEquals("https://www.saucedemo.com/inventory.html", invPage.getURL());
    }

}
