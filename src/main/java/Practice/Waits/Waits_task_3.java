package Practice.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Waits_task_3 {

    static void main() {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> imageList = wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#image-container img"), 3));
        String srcImage = imageList.get(2).getDomAttribute("src");

        System.out.println(srcImage);

        driver.quit();
    }
}
