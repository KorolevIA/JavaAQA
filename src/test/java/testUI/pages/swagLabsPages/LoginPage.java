package testUI.pages.swagLabsPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final WebDriver driver;

    @FindBy(css = "#user-name")
    private WebElement inputUsername;

    @FindBy(css = "#password")
    private WebElement inputPassword;

    @FindBy(css = "#login-button")
    private WebElement buttonLogin;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage loginInSwagLabs(String username, String password) {
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonLogin.click();
        return PageFactory.initElements(driver, MainPage.class);
    }

    public String getErrorMessage() {
        return driver.findElement(By.cssSelector(".error h3")).getText();
    }

}
