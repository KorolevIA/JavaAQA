package Practice.Selenide.Task_6.Pages;

import static com.codeborne.selenide.Selenide.$;

public class ResultPage {

    public String getTotalPrice() {
        return $(".summary_total_label").text();
    }

}
