package testUI.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import testUI.pages.RenameButtonPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenameButtonTests {

    private WebDriver driver;

    @BeforeEach
    public void SetUp() {
        this.driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @AfterEach
    public void quitDriver() {
        this.driver.quit();
    }

    @Test
    public void checkDefaultButtonName() {

        String defaultButtonName = "Button That Should Change it's Name Based on Input Value";

        RenameButtonPage page = PageFactory.initElements(driver, RenameButtonPage.class);
        page.open();
        String result = page.getButtonName();

        assertEquals(defaultButtonName, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Marion", "1234", "_test_"})
    public void renameButton(String newName) {

        RenameButtonPage page = PageFactory.initElements(driver, RenameButtonPage.class);
        page.open();
        page.setInput(newName);
        page.clickButton();
        String result = page.getButtonName();

        assertEquals(newName, result);
    }

}
