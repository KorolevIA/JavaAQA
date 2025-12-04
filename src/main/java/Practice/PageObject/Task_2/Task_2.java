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
        formPage.setInputFirstName("Иван");
        formPage.setInputLastName("Петров");
        formPage.setInputAddress("Ленина, 55-3");
        formPage.setInputCity("Москва");
        formPage.setInputCountry("Россия");
        formPage.setInputJobPosition("QA");
        formPage.setInputCompany("Merion");
        ResultFormPage resultFormPage = formPage.clickSubmit();
        String colorInputZipCode = resultFormPage.getColorInputZipCode();
        String colorInputEmail = resultFormPage.getColorInputEmail();
        String colorInputPhoneNumber = resultFormPage.getColorInputPhoneNumber();
        formPage.close();

        System.out.println(colorInputZipCode);
        System.out.println(colorInputEmail);
        System.out.println(colorInputPhoneNumber);
    }
}
