package Practice.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Waits_task_2 {

    static void main() {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://uitestingplayground.com/textinput");

        driver.findElement(By.cssSelector("#newButtonName")).sendKeys("Merion");
        driver.findElement(By.cssSelector("#updatingButton")).click();

        String textButton = driver.findElement(By.cssSelector("#updatingButton")).getText();
        System.out.println(textButton);

        driver.quit();
    }
}
