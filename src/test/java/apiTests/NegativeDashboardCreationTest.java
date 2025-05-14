package apiTests;

import apiTests.pojos.DashboardPojo;
import configuration.Setups;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Negative dashboard creation")
public class NegativeDashboardCreationTest extends PositiveDashboardCreationTest {

    @BeforeAll
    public static void setup() {
        expectedStatusCodeCreateDashboard = 400;
        expectedStatusCodeNewDashboard = 400;
        String dashboardName = "";
        String dashboardDescription = Setups.randomString();
        dashboard = new DashboardPojo(dashboardDescription, dashboardName);
        specification = Setups.requestSpec(dashboard);
    }

    @Test
    @Description(value = "Creating dashboard where field 'name' is not inserted")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Roman Vorozheikin")
    @Link(name = "Report portal", url = "https://demo.reportportal.io")
    @Order(1)
    public void createDashboardTest() {
        super.createDashboardTest();
    }

    @Test
    @Description(value = "Checking that dashboard is not created")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Roman Vorozheikin")
    @Link(name = "Report portal", url = "https://demo.reportportal.io")
    @Order(2)
    public void checkDashboardCreation() {
        super.checkDashboardCreation();
    }

    @Test
    @Description(value = "Checking that dashboard does not exist in the list")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Roman Vorozheikin")
    @Link(name = "Report portal", url = "https://demo.reportportal.io")
    @Order(3)
    public void checkDashboardExisting() {
        super.checkDashboardExisting();
    }
}
