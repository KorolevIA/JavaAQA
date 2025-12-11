package Practice.Selenide.Task_1.Pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private final SelenideElement button = $("#ajaxButton");
    private final SelenideElement resultText = $("#content p");

    public MainPage open() {
        Configuration.browser = "Firefox";
        Selenide.open("http://uitestingplayground.com/ajax");
        return this;
    }

    public MainPage clickButton() {
        button.click();
        return this;
    }

    public String getResultText() {
        return  resultText.shouldBe(visible, Duration.ofSeconds(16)).text();
    }

}
