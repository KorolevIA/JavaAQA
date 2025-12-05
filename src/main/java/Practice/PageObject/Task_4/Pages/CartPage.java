package Practice.PageObject.Task_4.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage{

    @FindBy(css = "#checkout")
    private WebElement buttonCheckout;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public FormPage clickCheckout() {
        buttonCheckout.click();
        return PageFactory.initElements(driver, FormPage.class);
    }

}
