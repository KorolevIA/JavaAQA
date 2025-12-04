package Practice.PageObject.Task_2.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FormPage {

    private final WebDriver driver;

    @FindBy(css = ".form-label")
    private List<WebElement> inputList;

    @FindBy(css = ".btn")
    private WebElement buttonSubmit;

    public FormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/data-types.html");
        driver.manage().window().maximize();
    }

    public void close() {
        driver.quit();
    }

    public void setInput(String inputName, String value) {
        for (WebElement input : inputList) {
            if (input.getText().equalsIgnoreCase(inputName)) {
                input.findElement(By.cssSelector("input")).sendKeys(value);
            }
        }
    }

    public ResultFormPage clickSubmit() {
        buttonSubmit.click();
        return PageFactory.initElements(driver, ResultFormPage.class);
    }

}
