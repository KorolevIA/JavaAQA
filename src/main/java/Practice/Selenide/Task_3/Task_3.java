package Practice.Selenide.Task_3;

import Practice.Selenide.Task_3.Pages.MainPage;

public class Task_3 {

    static void main() {

        String srcImg = new MainPage()
                .open()
                .getSrcImg(3);

        System.out.println(srcImg);
    }

}
