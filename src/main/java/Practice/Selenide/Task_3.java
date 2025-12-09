package Practice.Selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class Task_3 {

    static void main() {

        Configuration.browser = "Firefox";
        Configuration.timeout = 10*1000;

        open("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");

        ElementsCollection listImg = $$(".col-12 img").shouldHave(size(3));
        String srcImg = listImg.get(2).getAttribute("src");

        System.out.println(srcImg);
    }

}
