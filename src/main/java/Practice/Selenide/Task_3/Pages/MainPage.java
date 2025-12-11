package Practice.Selenide.Task_3.Pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    public MainPage open() {
        Configuration.browser = "Firefox";
        Selenide.open("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        return this;
    }

    public String getSrcImg(int num) {
        ElementsCollection listImg = $$(".col-12 img").shouldHave(size(num), Duration.ofSeconds(20));
        return listImg.get(num - 1).getAttribute("src");
    }

}
