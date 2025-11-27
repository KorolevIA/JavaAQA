package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Task_2 {

    static void main() {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://uitestingplayground.com/dynamicid");

        driver.findElement(By.cssSelector(".btn")).click();

        driver.quit();
    }
}
