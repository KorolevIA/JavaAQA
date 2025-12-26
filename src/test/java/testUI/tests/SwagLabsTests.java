package testUI.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import testUI.pages.swagLabsPages.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SwagLabsTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }

    // Tests

    @Test
    public void checkTotalPrice() {
        List<String> items = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie");

        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        MainPage mainPage = loginPage.loginInSwagLabs("standard_user", "secret_sauce");
        mainPage.addItemInCart(items);

        CartPage cartPage = mainPage.openCart();
        FormPage formPage = cartPage.clickCheckout();
        formPage.setForm("Иванов", "Иван", 150150);
        OverviewPage overviewPage = formPage.clickContinue();

        assertEquals("Total: $58.29", overviewPage.getTotalSum());
    }

    @Test
    public void loginLockedOutUser() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginInSwagLabs("locked_out_user", "secret_sauce");

        assertEquals("Epic sadface: Sorry, this user has been locked out.", loginPage.getErrorMessage());
    }

    @Test
    public void checkItemTitleInCart() {
        List<String> items = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Onesie", "Sauce Labs Bolt T-Shirt");

        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        MainPage mainPage = loginPage.loginInSwagLabs("standard_user", "secret_sauce");
        mainPage.addItemInCart(items);

        CartPage cartPage = mainPage.openCart();
        List<String> itemsTitles = cartPage.getItemsTitles();
        Collections.sort(items);
        Collections.sort(itemsTitles);

        assertEquals(items, itemsTitles);
    }

    @ParameterizedTest
    @MethodSource("getItemList")
    public void checkSizeCart(List<String> items) {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        MainPage mainPage = loginPage.loginInSwagLabs("standard_user", "secret_sauce");
        mainPage.addItemInCart(items);
        int sizeItemsOnCartIcon = mainPage.getSizeItemsOnCartIcon();

        CartPage cartPage = mainPage.openCart();
        int sizeCart = cartPage.getSizeCart();

        assertEquals(items.size(), sizeItemsOnCartIcon);
        assertEquals(items.size(), sizeCart);

    }

    static Stream<Arguments> getItemList() {
        return Stream.of(
              arguments(List.of("Sauce Labs Backpack")),
              arguments(Arrays.asList("Sauce Labs Backpack", "Sauce Labs Onesie")),
              arguments(Arrays.asList("Sauce Labs Backpack", "Sauce Labs Onesie", "Sauce Labs Bolt T-Shirt"))
        );
    }

}
