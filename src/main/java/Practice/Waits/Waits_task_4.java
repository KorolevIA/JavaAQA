package Practice.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Waits_task_4 {

    static void main() {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/data-types.html");

        driver.findElement(By.cssSelector("[name = 'first-name']")).sendKeys("Иван");
        driver.findElement(By.cssSelector("[name = 'last-name']")).sendKeys("Петров");
        driver.findElement(By.cssSelector("[name = 'address']")).sendKeys("Ленина, 55-3");
        driver.findElement(By.cssSelector("[name = 'city']")).sendKeys("Москва");
        driver.findElement(By.cssSelector("[name = 'country']")).sendKeys("Россия");
        driver.findElement(By.cssSelector("[name = 'job-position']")).sendKeys("QA");
        driver.findElement(By.cssSelector("[name = 'company']")).sendKeys("Merion");

        driver.findElement(By.cssSelector(".btn")).click();

        String zip_codeColor = driver.findElement(By.cssSelector("#zip-code")).getCssValue("background-color");
        String emailColor = driver.findElement(By.cssSelector("#e-mail")).getCssValue("background-color");
        String phoneNumberColor = driver.findElement(By.cssSelector("#phone")).getCssValue("background-color");

        System.out.println("Zip code -> " + zip_codeColor);
        System.out.println("E-mail -> " + emailColor);
        System.out.println("Phone number -> " + phoneNumberColor);

        driver.quit();
    }
}
