package uiTests;

import configuration.Setups;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import uiTests.pages.AccountPage;
import uiTests.pages.AuthPage;
import uiTests.pages.DashboardPage;
import uiTests.pages.WidgetPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Widget creation")
public class WidgetCreationTest {

    private static AuthPage authPage;
    private static AccountPage accountPage;
    private static DashboardPage dashboardPage;
    private static WidgetPage widgetPage;
    private static EventFiringWebDriver driver;
    private static String widgetName;

    @BeforeAll
    public static void setup() {
        driver = Setups.driver();
        authPage = new AuthPage(driver);
        accountPage = new AccountPage(driver);
        dashboardPage = new DashboardPage(driver);
        widgetPage = new WidgetPage(driver);
    }

    @AfterAll
    public static void afterTest() {
        driver.close();
        driver.quit();
    }


    @Test
    @DisplayName("Authentification")
    @Description("Log into the system")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Roman Vorozheikin")
    @Link(name = "Report portal", url = "https://demo.reportportal.io")
    @Order(1)
    public void autentification() {
        authPage.inputLogin(Setups.LOGIN);
        authPage.inputPassword(Setups.PASSWORD);
        authPage.pushLoginButton();
    }


    @Test
    @DisplayName("Dashboard operation")
    @Description("Moving into the existing dashboard")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Roman Vorozheikin")
    @Link(name = "Report portal", url = "https://demo.reportportal.io")
    @Order(2)
    public void dashboardOperations() {
        accountPage.clickDashboardButton();
        dashboardPage.clickDashboard();
    }


    @Test
    @DisplayName("Widget operations")
    @Description("Creating of a new widget")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Roman Vorozheikin")
    @Link(name = "Report portal", url = "https://demo.reportportal.io")
    @Order(3)
    public void widgetOperations() {
        dashboardPage.addWidget();
        widgetPage.selectType(Setups.WIDGET_TYPE);
        widgetPage.configureWidget();
        widgetName = widgetPage.saveWidget();
    }

    @Test
    @Description("Checking that widget is created")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Roman Vorozheikin")
    @Link(name = "Report portal", url = "https://demo.reportportal.io")
    @Order(4)
    public void checkingWidget() {
        boolean widgetIsCreated = widgetPage.checkWidget(widgetName);
        Assertions.assertEquals(true, widgetIsCreated);
    }
}
