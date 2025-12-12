package Practice.Selenide.Task_6;

import Practice.Selenide.Task_6.Pages.*;

import java.util.Arrays;
import java.util.List;

public class Task_6 {

    static void main() {

        List<String> items = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie");

        new LoginPage()
                .open()
                .loginInShop("standard_user", "secret_sauce");

        MainPage mainPage = new MainPage();
        mainPage.addItemInCart(items);

        CartPage cart = mainPage.openCart();
        FormPage form = cart.clickCheckout();
        ResultPage resultPage = form
                .setForm("Иван", "Иванов", 140140)
                .clickContinue();

        String priceTotal = resultPage.getTotalPrice();

        System.out.println(priceTotal);
    }

}
