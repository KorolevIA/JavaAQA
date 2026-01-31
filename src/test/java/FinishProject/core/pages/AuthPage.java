package FinishProject.core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthPage {

    private final WebDriver driver;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openSwagLabs(String url) {
        driver.get(url);
    }

    public InventoryPage authInSwagLabs(String login, String password) {
        driver.findElement(By.cssSelector("#user-name")).sendKeys(login);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#login-button")).click();
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(By.cssSelector(".error-message-container h3")).getText();
    }

}
