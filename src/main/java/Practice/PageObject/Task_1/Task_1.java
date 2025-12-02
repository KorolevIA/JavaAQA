package Practice.PageObject.Task_1;

import Practice.PageObject.Task_1.Pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class Task_1 {

    static void main() {

        WebDriver driver = new FirefoxDriver();

        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.open();
        mainPage.setInput("Merion");
        mainPage.clickButton();
        String result = mainPage.getButtonName();
        mainPage.close();

        System.out.println(result);
    }
}
