package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class Task_1 {

    static void main() {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");

        WebElement add_element = driver.findElement(By.cssSelector("[onclick = 'addElement()']"));
        for (int i = 0; i < 5; i++) {
            add_element.click();
        }

        List<WebElement> elements = driver.findElements(By.cssSelector("#elements button"));
        System.out.println(elements.size());

        driver.quit();

    }
}
