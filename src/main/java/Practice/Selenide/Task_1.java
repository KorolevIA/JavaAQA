package Practice.Selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Task_1 {

    static void main() {

        Configuration.browser = "Firefox";
        Configuration.timeout = 16*1000;

        open("http://uitestingplayground.com/ajax");

        $("#ajaxButton").click();
        String resultText = $("#content p").shouldBe(Condition.visible).text();

        System.out.println(resultText);
    }

}
