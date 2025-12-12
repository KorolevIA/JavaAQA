package Practice.Selenide.Task_6.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CartPage {

    private final SelenideElement buttonCheckout = $("#checkout");

    public FormPage clickCheckout() {
        buttonCheckout.click();
        return new FormPage();
    }

}
