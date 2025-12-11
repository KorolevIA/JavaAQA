package Practice.Selenide.Task_4.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class ResultPage {

    private final ElementsCollection inputList = $$(".container label");

    public String getColorInput(String inputName) {
        String response = "Элемент не найден";

        for (SelenideElement input : inputList) {
            if (input.text().toLowerCase().startsWith(inputName.toLowerCase())) {
                response = input.find("div").getCssValue("background-color");
            }
        }
        return response;
    }

}
