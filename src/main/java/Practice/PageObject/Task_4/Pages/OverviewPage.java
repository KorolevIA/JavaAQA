package Practice.PageObject.Task_4.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OverviewPage extends BasePage {

    @FindBy(css = ".summary_total_label")
    private WebElement totalSum;

    public OverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getTotalSum() {
        return totalSum.getText();
    }

}
