package Practice.PageObject.Task_4.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends BasePage {

    @FindBy(css = "#first-name")
    private WebElement inputFirstName;

    @FindBy(css = "#last-name")
    private WebElement inputLastName;

    @FindBy(css = "#postal-code")
    private WebElement inputZipCode;

    @FindBy(css = "#continue")
    private WebElement buttonContinue;

    public FormPage(WebDriver driver) {
        super(driver);
    }

    public void setForm(String firstName, String lastName, int zipCode) {
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputZipCode.sendKeys(String.valueOf(zipCode));
    }

    public OverviewPage clickContinue() {
        buttonContinue.click();
        return PageFactory.initElements(driver, OverviewPage.class);
    }

}
