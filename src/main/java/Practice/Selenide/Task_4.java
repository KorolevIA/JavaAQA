package Practice.Selenide;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Task_4 {

    static void main() {

        Configuration.browser = "Firefox";

        open("https://bonigarcia.dev/selenium-webdriver-java/data-types.html");

        $("[name = 'first-name']").setValue("Иван");
        $("[name = 'last-name']").setValue("Петров");
        $("[name = 'address']").setValue("Ленина, 55-3");
        $("[name = 'city']").setValue("Москва");
        $("[name = 'country']").setValue("Россия");
        $("[name = 'job-position']").setValue("QA");
        $("[name = 'company']").setValue("Merion");
        $(".btn").click();

        String colorZipCode = $("#zip-code").getCssValue("background-color");
        String colorEmail = $("#e-mail").getCssValue("background-color");
        String colorPhone = $("#phone").getCssValue("background-color");

        System.out.println(colorZipCode);
        System.out.println(colorEmail);
        System.out.println(colorPhone);
    }

}
