package Practice.Selenide.Task_6.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FormPage {

    private final SelenideElement inputFirstName = $("#first-name");
    private final SelenideElement inputLastName = $("#last-name");
    private final SelenideElement inputZipCode = $("#postal-code");
    private final SelenideElement buttonContinue = $("#continue");

    public FormPage setForm(String firstName, String lastName, int zipCode) {
        inputFirstName.setValue(firstName);
        inputLastName.setValue(lastName);
        inputZipCode.setValue(String.valueOf(zipCode));
        return this;
    }

    public ResultPage clickContinue() {
        buttonContinue.click();
        return new ResultPage();
    }

}
