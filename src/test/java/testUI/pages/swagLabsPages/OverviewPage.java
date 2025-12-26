package testUI.pages.swagLabsPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OverviewPage {

    @FindBy(css = ".summary_total_label")
    private WebElement totalSum;

    public String getTotalSum() {
        return totalSum.getText();
    }

}
