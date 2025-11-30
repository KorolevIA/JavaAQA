package Practice.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class Waits_task_6 {

    static void main() {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();

        List<WebElement> inventoreList = driver.findElements(By.cssSelector(".inventory_item"));
        inventoreList.get(0).findElement(By.cssSelector("button")).click();
        inventoreList.get(2).findElement(By.cssSelector("button")).click();
        inventoreList.get(4).findElement(By.cssSelector("button")).click();

        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.cssSelector("#checkout")).click();

        driver.findElement(By.cssSelector("#first-name")).sendKeys("Jon");
        driver.findElement(By.cssSelector("#last-name")).sendKeys("Waiter");
        driver.findElement(By.cssSelector("#postal-code")).sendKeys("140140");
        driver.findElement(By.cssSelector("#continue")).click();

        String priceTotal = driver.findElement(By.cssSelector(".summary_total_label")).getText();
        System.out.println(priceTotal);

        driver.quit();
    }
}
