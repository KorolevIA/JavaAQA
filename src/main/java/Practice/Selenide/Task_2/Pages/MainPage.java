package Practice.Selenide.Task_2.Pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private final SelenideElement inputNewButtonName = $("#newButtonName");
    private final SelenideElement updatingButton = $("#updatingButton");

    public MainPage open() {
        Configuration.browser = "Firefox";
        Selenide.open("http://uitestingplayground.com/textinput");
        return this;
    }

    public MainPage setButtonName(String newName) {
        inputNewButtonName.setValue(newName);
        return this;
    }

    public MainPage clickButton() {
        updatingButton.click();
        return this;
    }

    public String getButtonName() {
        return updatingButton.text();
    }

}
