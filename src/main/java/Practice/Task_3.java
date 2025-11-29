package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Task_3 {

    static void main() {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://uitestingplayground.com/classattr");

        driver.findElement(By.cssSelector(".btn-primary")).click();

        driver.quit();
    }
}
