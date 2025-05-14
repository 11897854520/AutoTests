package uiTests.pages;

import configuration.Setups;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DashboardPage {

    public DashboardPage(EventFiringWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private static EventFiringWebDriver driver;
    @FindBy(xpath = "//a[contains(@href, '#default_personal/dashboard/')]")
    private static WebElement dashboards;
    @FindBy(xpath = "//span[text()= 'Add New Dashboard']")
    private static WebElement addDashboardButton;
    @FindBy(xpath = "//input[contains(@placeholder, 'Enter dashboard name')]")
    private static WebElement dashboardNameField;
    @FindBy(xpath = "//span[text()= 'Add new widget']")
    private static WebElement addWidgetButton;


    @Step("Selecting of a existing dashboard, or creating of a new dashboard if there is not " +
            "a existing dashboard...")
    public void clickDashboard() {
        try {
            driver.executeScript("arguments[0].click();", dashboards);
        } catch (Exception e) {
            String dashboardName = "My dashboard";
            createDashboard(dashboardName);
        }
        Setups.makeScreenshot(driver);
    }

    @Step("Clicking of the button 'Add widget'...")
    public void addWidget() {
        Setups.makeScreenshot(driver);
        driver.executeScript("arguments[0].click();", addWidgetButton);
    }



    private void createDashboard(String dashboardName) {
        addDashboardButton.click();
        dashboardNameField.sendKeys(dashboardName);
        WidgetPage.addButton.click();
    }

}
