package Practice.Selenide.Task_5.Pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    Map<String, SelenideElement> keyMap = new HashMap<>();
    int timeout;

    private void getKeyMap() {
        ElementsCollection keyList = $$(".keys span");

        for (SelenideElement selenideElement : keyList) {
            keyMap.put(selenideElement.text(), selenideElement);
        }
    }

    public void open () {
        Configuration.browser = "Firefox";
        Selenide.open("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");
    }

    public void setInputWaits(int second) {
        timeout = second;
        $("#delay").setValue(String.valueOf(timeout));
    }

    public void kuyClick(String key) {
        if (keyMap.isEmpty()) {
            getKeyMap();
        }
        keyMap.get(key).click();
    }

    public String getResult() {
        $("#spinner").shouldBe(not(visible), Duration.ofSeconds(timeout + 1));
        return $(".screen").text();
    }

}
