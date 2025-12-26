package testUI.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import testUI.pages.CalculatorPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkAddition() {

        CalculatorPage calcPage = PageFactory.initElements(driver, CalculatorPage.class);
        calcPage.open();
        calcPage.setInputWaits(15);
        calcPage.keyClick("7");
        calcPage.keyClick("+");
        calcPage.keyClick("8");
        calcPage.keyClick("=");

        assertEquals("15", calcPage.getResult());
    }

    @Test
    public void checkSubtraction() {

        CalculatorPage calcPage = PageFactory.initElements(driver, CalculatorPage.class);
        calcPage.open();
        calcPage.setInputWaits(6);
        calcPage.keyClick("1");
        calcPage.keyClick("8");
        calcPage.keyClick("-");
        calcPage.keyClick("6");
        calcPage.keyClick("=");

        assertEquals("12", calcPage.getResult());
    }

}
