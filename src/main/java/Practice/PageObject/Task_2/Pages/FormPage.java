package Practice.PageObject.Task_2.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormPage {

    private final WebDriver driver;

    @FindBy(css = "[name = 'first-name']")
    private WebElement inputFirstName;

    @FindBy(css = "[name = 'last-name']")
    private WebElement inputLastName;

    @FindBy(css = "[name = 'address']")
    private WebElement inputAddress;

    @FindBy(css = "[name = 'city']")
    private WebElement inputCity;

    @FindBy(css = "[name = 'country']")
    private WebElement inputCountry;

    @FindBy(css = "[name = 'job-position']")
    private WebElement inputJobPosition;

    @FindBy(css = "[name = 'company']")
    private WebElement inputCompany;

    @FindBy(css = ".btn")
    private WebElement buttonSubmit;

    public FormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/data-types.html");
        driver.manage().window().maximize();
    }

    public void close() {
        driver.quit();
    }

    public void setInputFirstName(String firstName) {
        inputFirstName.sendKeys(firstName);
    }

    public void setInputLastName(String lastName) {
        inputLastName.sendKeys(lastName);
    }

    public void setInputAddress(String address) {
        inputAddress.sendKeys(address);
    }
    public void setInputCity(String city) {
        inputCity.sendKeys(city);
    }

    public void setInputCountry(String country) {
        inputCountry.sendKeys(country);
    }

    public void setInputJobPosition(String jobPosition) {
        inputJobPosition.sendKeys(jobPosition);
    }

    public void setInputCompany(String company) {
        inputCompany.sendKeys(company);
    }

    public ResultFormPage clickSubmit() {
        buttonSubmit.click();
        return PageFactory.initElements(driver, ResultFormPage.class);
    }
}
