package testUI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatorPage {

    private final WebDriver driver;

    private int timeout = 5;

    private Map<String, WebElement> keyMap = new HashMap<>();

    @FindBy(css = "#delay")
    private WebElement inputWaits;

    @FindBy(css = "#spinner")
    private WebElement spinner;

    @FindBy(css = ".screen")
    private WebElement inputResult;

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    private void getKeyMap() {
        List<WebElement> elementsList = driver.findElements(By.cssSelector(".keys span"));
        for (WebElement element : elementsList) {
            keyMap.put(element.getText(), element);
        }
    }

    public void open() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");
    }

    public void setInputWaits(int second) {
        timeout = second;
        inputWaits.clear();
        inputWaits.sendKeys(String.valueOf(timeout));
    }

    public void keyClick(String key) {
        if (keyMap.isEmpty()) {
            getKeyMap();
        }
        keyMap.get(key).click();
    }

    public String getResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout + 1));
        wait.until(ExpectedConditions.invisibilityOf(spinner));

        return inputResult.getText();
    }

}
