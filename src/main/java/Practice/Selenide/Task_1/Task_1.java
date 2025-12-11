package Practice.Selenide.Task_1;

import Practice.Selenide.Task_1.Pages.MainPage;

public class Task_1 {

    static void main() {

        String resultText = new MainPage()
                .open()
                .clickButton()
                .getResultText();

        System.out.println(resultText);
    }

}
