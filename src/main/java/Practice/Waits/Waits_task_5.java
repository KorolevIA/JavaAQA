package Practice.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Waits_task_5 {

    static void main() {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");

        WebElement inputWaits = driver.findElement(By.cssSelector("#delay"));
        inputWaits.clear();
        inputWaits.sendKeys("45");

        List <WebElement> elements = driver.findElements(By.cssSelector(".keys span"));
        Map <String, WebElement> calculatorButtons = new HashMap<>();
        for (WebElement element : elements) {
            calculatorButtons.put(element.getText(), element);
        }

        calculatorButtons.get("7").click();
        calculatorButtons.get("+").click();
        calculatorButtons.get("8").click();
        calculatorButtons.get("=").click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#spinner"))));
        String result = driver.findElement(By.cssSelector(".screen")).getText();

        System.out.println("Result: " + result);

        driver.quit();
    }
}
