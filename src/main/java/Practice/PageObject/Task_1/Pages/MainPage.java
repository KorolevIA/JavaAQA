package Practice.PageObject.Task_1.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {

    private final WebDriver driver;

    @FindBy(css = "#newButtonName")
    private WebElement input;

    @FindBy(css = "#updatingButton")
    private WebElement button;

    public MainPage (WebDriver driver) {
        this.driver = driver;
    }

    public void open () {
        driver.get("http://uitestingplayground.com/textinput");
        driver.manage().window().maximize();
    }

    public void close () {
        driver.quit();
    }

    public void setInput (String text) {
        input.sendKeys(text);
    }

    public void clickButton () {
        button.click();
    }

    public String getButtonName () {
        return button.getText();
    }
}
