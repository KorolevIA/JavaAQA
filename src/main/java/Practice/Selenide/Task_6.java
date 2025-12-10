package Practice.Selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.*;

public class Task_6 {

    static void main() {

        Configuration.browser = "Firefox";

        open("https://www.saucedemo.com/");

        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce");
        $("#login-button").click();

        ElementsCollection itemList = $$(".inventory_item");
        itemList.get(0).find("button").click();
        itemList.get(2).find("button").click();
        itemList.get(4).find("button").click();

        $(".shopping_cart_link").click();
        $("#checkout").click();

        $("#first-name").setValue("Jon");
        $("#last-name").setValue("Waiter");
        $("#postal-code").setValue("140140");
        $("#continue").click();

        String priceTotal = $(".summary_total_label").text();

        System.out.println(priceTotal);
    }

}
