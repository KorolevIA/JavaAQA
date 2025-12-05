package Practice.PageObject.Task_4.Pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    public void close() {
        driver.quit();
    }

}
