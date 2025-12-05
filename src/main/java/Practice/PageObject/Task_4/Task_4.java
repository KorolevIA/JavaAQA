package Practice.PageObject.Task_4;

import Practice.PageObject.Task_4.Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class Task_4 {

    static void main() {

        WebDriver driver = new FirefoxDriver();

        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.open();
        MainPage mainPage = loginPage.loginInSwagLabs("standard_user", "secret_sauce");
        mainPage.addItemInCart("Sauce Labs Backpack");
        mainPage.addItemInCart("Sauce Labs Bolt T-Shirt");
        mainPage.addItemInCart("Sauce Labs Onesie");
        CartPage cartPage = mainPage.openCart();
        FormPage formPage = cartPage.clickCheckout();
        formPage.setForm("Иванов", "Иван", 140100);
        OverviewPage overviewPage = formPage.clickContinue();
        String totalSum = overviewPage.getTotalSum();
        overviewPage.close();

        System.out.println(totalSum);
    }

}
