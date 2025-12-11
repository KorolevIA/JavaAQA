package Practice.Selenide.Task_2;

import Practice.Selenide.Task_2.Pages.MainPage;

public class Task_2 {

    static void main() {

        String buttonName = new MainPage()
                .open()
                .setButtonName("Merion")
                .clickButton()
                .getButtonName();

        System.out.println(buttonName);
    }
    
}
