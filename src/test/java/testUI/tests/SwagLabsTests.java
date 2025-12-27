package testUI.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import testUI.pages.swagLabsPages.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(DriverResolver.class)
public class SwagLabsTests {

    @Test
    public void checkTotalPrice(WebDriver driver) {
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
    public void loginLockedOutUser(WebDriver driver) {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginInSwagLabs("locked_out_user", "secret_sauce");

        assertEquals("Epic sadface: Sorry, this user has been locked out.", loginPage.getErrorMessage());
    }

    @Test
    public void checkItemTitleInCart(WebDriver driver) {
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
    public void checkSizeCart(List<String> items, WebDriver driver) {
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
