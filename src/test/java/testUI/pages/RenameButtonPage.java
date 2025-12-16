package testUI.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RenameButtonPage {

    private final WebDriver driver;

    @FindBy(css = "#newButtonName")
    private WebElement input;

    @FindBy(css = "#updatingButton")
    private WebElement button;

    public RenameButtonPage (WebDriver driver) {
        this.driver = driver;
    }

    public void open () {
        driver.get("http://uitestingplayground.com/textinput");
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
