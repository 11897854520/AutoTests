package uiTests.pages;
import configuration.Setups;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class AccountPage {

    public AccountPage(EventFiringWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private static EventFiringWebDriver driver;
    @FindBy(xpath = "//a[contains(@href, '#default_personal/dashboard')]")
    private static WebElement dashboardButton;

    @Step("Opening of the page with a existing dashboards...")
    public void clickDashboardButton() {
        dashboardButton.click();
        Setups.makeScreenshot(driver);
    }

}
