package testUI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultFormPage {

    @FindBy(css = ".form-label")
    private List<WebElement> inputList;

    public String getColorInput(String inputName) {
        String result = "Элемент не найден";

        for (WebElement input : inputList) {
            if (input.getText().toLowerCase().startsWith(inputName.toLowerCase())) {
                result = input.findElement(By.cssSelector(".alert")).getCssValue("background-color");
            }
        }
        return result;
    }

}
