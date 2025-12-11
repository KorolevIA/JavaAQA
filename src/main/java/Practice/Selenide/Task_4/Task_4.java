package Practice.Selenide.Task_4;

import Practice.Selenide.Task_4.Pages.FormPage;
import Practice.Selenide.Task_4.Pages.ResultPage;

public class Task_4 {

    static void main() {

        FormPage form = new FormPage();
        form.open();

        form.setInput("First name", "Иван");
        form.setInput("Last Name", "Петров");
        form.setInput("Address", "Ленина, 55-3");
        form.setInput("City", "Москва");
        form.setInput("Country", "Россия");
        form.setInput("Job position", "QA");
        form.setInput("Company", "Merion");

        ResultPage resultPage = form.clickButton();

        String colorZipCode = resultPage.getColorInput("Zip code");
        String colorEmail = resultPage.getColorInput("E-mail");
        String colorPhone = resultPage.getColorInput("Phone number");

        System.out.println(colorZipCode);
        System.out.println(colorEmail);
        System.out.println(colorPhone);
    }

}
