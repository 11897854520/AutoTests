package uiTests.pages;

import configuration.Setups;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class AuthPage {

    public  AuthPage (EventFiringWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private EventFiringWebDriver driver;
    @FindBy(name = "login")
    private WebElement loginField;
    @FindBy(name = "password")
    private WebElement passwordField;
    @FindBy(xpath = "//button[text()= 'Login']")
    private WebElement loginButton;

    @Step("Inserting of the username...")
    public void inputLogin(String login) {
        loginField.clear();
        loginField.sendKeys(login);
        Setups.makeScreenshot(driver);
    }
    @Step("Inserting of the password...")
    public void inputPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        Setups.makeScreenshot(driver);
    }
    @Step("Clicking on the button 'Login'")
    public void pushLoginButton() {
        loginButton.click();
        Setups.makeScreenshot(driver);
    }
}
