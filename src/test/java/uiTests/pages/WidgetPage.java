package uiTests.pages;

import configuration.Setups;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
public class WidgetPage {

    public WidgetPage(EventFiringWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private static EventFiringWebDriver driver;

    @FindBy(xpath = "//span[contains(@class, 'filterName__name--B4z4P')]")
    private static  WebElement selectFilter;
    @FindBy(xpath = "//span[text()= 'Next step']")
    private static WebElement nextStep;
    @FindBy(xpath = "//input[contains(@placeholder, 'Enter widget name')]")
    private static WebElement widgetName;
    @FindBy(xpath = "//button[@type= 'button' and text()= 'Add']")
    protected static WebElement addButton;


    @Step("Selecting of the widget type...")
    public void selectType(String type) {
        Setups.makeScreenshot(driver);
        WebElement typeRadio = driver.findElement(By.xpath("//div[text()= '" + type + "']"));
        typeRadio.click();
        nextStep.click();
    }

    @Step("Selecting of the filter...")
    public void configureWidget() {
        Setups.makeScreenshot(driver);
        selectFilter.click();
        driver.executeScript("arguments[0].click();", nextStep);
    }

    @Step("Saving of the widget, and getting its name")
    public String saveWidget() {
        String name = widgetName.getAttribute("value");
        addButton.click();
        Setups.makeScreenshot(driver);
       return name;
    }

    @Step("Checking that the widget is really exists")
    public boolean checkWidget(String name) {
        WebElement widget = driver.findElement(By.xpath("//div[text()= '"
                + name + "']"));
        Setups.makeScreenshot(driver);
        return widget.isDisplayed();
    }

}
