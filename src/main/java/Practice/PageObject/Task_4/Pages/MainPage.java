package Practice.PageObject.Task_4.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(css = ".inventory_item")
    private List<WebElement> itemList;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void addItemInCart(String itemName) {
        for (WebElement item : itemList) {
            if (item.findElement(By.cssSelector(".inventory_item_name"))
                    .getText().equalsIgnoreCase(itemName)) {
                item.findElement(By.cssSelector("button")).click();
            }
        }
    }

    public CartPage openCart() {
        cartLink.click();
        return PageFactory.initElements(driver, CartPage.class);
    }

}
