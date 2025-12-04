package Practice.PageObject.Task_2;

import Practice.PageObject.Task_2.Pages.FormPage;
import Practice.PageObject.Task_2.Pages.ResultFormPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class Task_2 {

    static void main() {

        WebDriver driver = new FirefoxDriver();

        FormPage formPage = PageFactory.initElements(driver, FormPage.class);
        formPage.open();
        formPage.setInput("First name","Иван");
        formPage.setInput("Last name","Петров");
        formPage.setInput("Address","Ленина, 55-3");
        formPage.setInput("City","Москва");
        formPage.setInput("Country","Россия");
        formPage.setInput("Job position","QA");
        formPage.setInput("Company","Merion");
        ResultFormPage resultFormPage = formPage.clickSubmit();
        String colorInputZipCode = resultFormPage.getColorInput("Zip code");
        String colorInputEmail = resultFormPage.getColorInput("E-mail");
        String colorInputPhoneNumber = resultFormPage.getColorInput("Phone number");
        formPage.close();

        System.out.println(colorInputZipCode);
        System.out.println(colorInputEmail);
        System.out.println(colorInputPhoneNumber);
    }
}
