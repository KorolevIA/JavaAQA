package Practice.PageObject.Task_2.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultFormPage {

    @FindBy(css = "#zip-code")
    private WebElement inputZipCode;

    @FindBy(css = "#e-mail")
    private WebElement inputEmail;

    @FindBy(css = "#phone")
    private WebElement inputPhoneNumber;

    public String getColorInputZipCode() {
        return inputZipCode.getCssValue("background-color");
    }

    public String getColorInputEmail() {
        return inputEmail.getCssValue("background-color");
    }

    public String getColorInputPhoneNumber() {
        return inputPhoneNumber.getCssValue("background-color");
    }
}
