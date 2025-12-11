package Practice.Selenide.Task_4.Pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FormPage {

    private final ElementsCollection inputList = $$("form label");
    private final SelenideElement buttonSubmit = $(".btn");

    public void open() {
        Configuration.browser = "Firefox";
        Selenide.open("https://bonigarcia.dev/selenium-webdriver-java/data-types.html");
    }

    public void setInput(String inputName, String text) {
        for (SelenideElement input : inputList) {
            if (input.text().equalsIgnoreCase(inputName)) {
                input.find("input").setValue(text);
            }
        }
    }

    public ResultPage clickButton() {
        buttonSubmit.click();
        return new ResultPage();
    }

}
