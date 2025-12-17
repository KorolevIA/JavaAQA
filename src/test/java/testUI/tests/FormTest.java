package testUI.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import testUI.pages.FormPage;
import testUI.pages.ResultFormPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void setForm() {

        FormPage formPage = PageFactory.initElements(driver, FormPage.class);
        formPage.open();
        formPage.setInput("First name","Иван");
        formPage.setInput("Last name","Петров");
        formPage.setInput("Address","Ленина, 55-3");
        formPage.setInput("City","Москва");
        formPage.setInput("Country","Россия");
        formPage.setInput("Job position","QA");
        formPage.setInput("Company","Merion");
        ResultFormPage resultFormPage = formPage.clickSubmit();

        assertEquals("rgb(248, 215, 218)", resultFormPage.getColorInput("Zip code"));
        assertEquals("rgb(248, 215, 218)", resultFormPage.getColorInput("E-mail"));
        assertEquals("rgb(248, 215, 218)", resultFormPage.getColorInput("Phone number"));
    }

}
