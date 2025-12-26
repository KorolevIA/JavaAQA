package testUI.pages.swagLabsPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {

    private final WebDriver driver;

    @FindBy(css = ".inventory_item")
    private List<WebElement> itemList;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addItemInCart(List<String> items) {
        for (WebElement item : itemList) {
            if (items.contains(item.findElement(By.cssSelector(".inventory_item_name")).getText())) {
                item.findElement(By.cssSelector("button")).click();
            }
        }
    }

    public CartPage openCart() {
        cartLink.click();
        return PageFactory.initElements(driver, CartPage.class);
    }

    public int getSizeItemsOnCartIcon() {
        return Integer.parseInt(driver.findElement(By.cssSelector(".shopping_cart_badge")).getText());
    }

}
