package Practice.Selenide;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Task_2 {

    static void main() {

        Configuration.browser = "Firefox";

        open("http://uitestingplayground.com/textinput");

        $("#newButtonName").setValue("Merion");
        $("#updatingButton").click();
        String nameButton = $("#updatingButton").text();

        System.out.println(nameButton);
    }
    
}
