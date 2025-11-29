package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Task_4 {

    static void main() throws InterruptedException {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/entry_ad");

        Thread.sleep(1000);

        driver.findElement(By.cssSelector(".modal-footer p")).click();
        String contentText = driver.findElement(By.cssSelector("#content")).getText();

        System.out.println(contentText);

        driver.quit();
    }
}
