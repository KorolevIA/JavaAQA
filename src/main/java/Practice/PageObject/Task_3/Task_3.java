package Practice.PageObject.Task_3;

import Practice.PageObject.Task_3.Pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class Task_3 {

    static void main() {

        WebDriver driver = new FirefoxDriver();

        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.open();
        mainPage.setInputWaits(15);
        mainPage.keyClick("7");
        mainPage.keyClick("+");
        mainPage.keyClick("8");
        mainPage.keyClick("=");
        String result = mainPage.getResult();
        mainPage.close();

        System.out.println(result);
    }
}
