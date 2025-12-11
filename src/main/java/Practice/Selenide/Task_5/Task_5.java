package Practice.Selenide.Task_5;

import Practice.Selenide.Task_5.Pages.MainPage;

public class Task_5 {

    static void main() {

        MainPage mainPage = new MainPage();

        mainPage.open();
        mainPage.setInputWaits(15);
        mainPage.kuyClick("7");
        mainPage.kuyClick("+");
        mainPage.kuyClick("8");
        mainPage.kuyClick("=");
        String result = mainPage.getResult();

        System.out.println(result);
    }

}
