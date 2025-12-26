package testUI.pages.swagLabsPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage {

    private final WebDriver driver;

    @FindBy(css = "#checkout")
    private WebElement buttonCheckout;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public FormPage clickCheckout() {
        buttonCheckout.click();
        return PageFactory.initElements(driver, FormPage.class);
    }

    public int getSizeCart() {
        return driver.findElements(By.cssSelector(".cart_item")).size();
    }

    public List<String> getItemsTitles() {
        List<String> itemsTitles = new ArrayList<>();

        List<WebElement> items = driver.findElements(By.cssSelector(".inventory_item_name"));
        for (WebElement item : items) {
            itemsTitles.add(item.getText());
        }

        return itemsTitles;
    }

}
