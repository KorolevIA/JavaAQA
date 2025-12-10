package Practice.Selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Task_5 {

    static void main() {

        Configuration.browser = "Firefox";
        Configuration.timeout = 16*1000;

        open("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");

        SelenideElement inputWaits = $("#delay");
        inputWaits.clear();
        inputWaits.setValue("15");

        ElementsCollection keyList = $$(".keys span");
        Map<String, SelenideElement> keyMap = new HashMap<>();

        for (SelenideElement selenideElement : keyList) {
            keyMap.put(selenideElement.text(), selenideElement);
        }

        keyMap.get("7").click();
        keyMap.get("+").click();
        keyMap.get("8").click();
        keyMap.get("=").click();

        $("#spinner").shouldBe(not(visible));
        String result = $(".screen").text();

        System.out.println(result);
    }

}
