package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Task_5 {

    static void main() {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/inputs");

        WebElement input = driver.findElement(By.cssSelector("input"));
        input.sendKeys("1000");
        input.clear();
        input.sendKeys("2000");

        driver.quit();
    }
}
