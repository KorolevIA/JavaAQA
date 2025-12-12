package Practice.Selenide.Task_6.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private final ElementsCollection itemList = $$(".inventory_item");
    private final SelenideElement cartIcon = $(".shopping_cart_link");

    public void addItemInCart(List<String> items) {
        for (SelenideElement item : itemList) {
            if (items.contains(item.find(".inventory_item_name").text())) {
                item.find("button").click();
            }
        }
    }

    public CartPage openCart() {
        cartIcon.click();
        return new CartPage();
    }

}
