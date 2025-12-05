package Practice.PageObject.Task_4.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(css = "#user-name")
    private WebElement inputUsername;

    @FindBy(css = "#password")
    private WebElement inputPassword;

    @FindBy(css = "#login-button")
    private WebElement buttonLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MainPage loginInSwagLabs(String username, String password) {
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonLogin.click();
        return PageFactory.initElements(driver, MainPage.class);
    }

}
