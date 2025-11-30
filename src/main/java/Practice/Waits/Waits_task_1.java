package Practice.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits_task_1 {

    static void main() {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://uitestingplayground.com/ajax");

        driver.findElement(By.cssSelector("#ajaxButton")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String resultText = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".bg-success"))).getText();

        System.out.println(resultText);

        driver.quit();
    }
}
