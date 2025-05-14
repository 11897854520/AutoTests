package apiTests;

import apiTests.pojos.DashboardPojo;
import configuration.Setups;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Positive dashboard creation")
public class PositiveDashboardCreationTest {

    protected static DashboardPojo dashboard;
    protected static RequestSpecification specification;
    protected static final String REQUEST = "/dashboard";
    protected static int expectedStatusCodeCreateDashboard;
    protected static int expectedStatusCodeNewDashboard;
    protected static String dashboardId;
    protected static int currentStatusCodeCreateDashboard;

    @BeforeAll
    public static void setup() {
        expectedStatusCodeCreateDashboard = 201;
        expectedStatusCodeNewDashboard = 200;
        String dashboardName = Setups.randomString();
        String dashboardDescription = Setups.randomString();
        dashboard = new DashboardPojo(dashboardDescription, dashboardName);
        specification = Setups.requestSpec(dashboard);
    }


    @Test
    @Description(value = "POST request execution with necessary information")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Roman Vorozheikin")
    @Link(name = "Report portal", url = "https://demo.reportportal.io")
    @Order(1)
    public void createDashboardTest() {
        String stepOne = Setups.stepName(1, dashboard, null, ""
                , 0, 0);
        while (true) {
            try {
                Allure.step(stepOne);
                Response response = RestAssured
                        .given()
                        .spec(specification)
                        .when().post(REQUEST);
                dashboardId = "/" + response.then().extract().jsonPath().getString("id");
                currentStatusCodeCreateDashboard = response.statusCode();
                String stepTwo = Setups.stepName(2, dashboard, null, dashboardId
                        , 0, currentStatusCodeCreateDashboard);
                Allure.step(stepTwo);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    @Description(value = "Checking that dashboard is created")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Roman Vorozheikin")
    @Link(name = "Report portal", url = "https://demo.reportportal.io")
    @Order(2)
    public void checkDashboardCreation() {
        String step = Setups.stepName(3, dashboard, null, dashboardId
                , expectedStatusCodeCreateDashboard, currentStatusCodeCreateDashboard);
        Allure.step(step);
        Assertions.assertEquals(expectedStatusCodeCreateDashboard, currentStatusCodeCreateDashboard);
    }

    @Test
    @Description(value = "Checking that dashboard exists in the list")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Roman Vorozheikin")
    @Link(name = "Report portal", url = "https://demo.reportportal.io")
    @Order(3)
    public void checkDashboardExisting() {
        String stepOne = Setups.stepName(4, dashboard, null, dashboardId
                , 0, 0);
        Allure.step(stepOne);
        while (true) {
            try {
                Response response = RestAssured
                        .given()
                        .spec(specification)
                        .when().get(REQUEST + dashboardId);
                int newDashboardStatusCode = response.then().extract().statusCode();
                DashboardPojo newDashboard = response.then().extract().as(DashboardPojo.class);
                String stepTwo = Setups.stepName(5, dashboard, newDashboard, dashboardId
                        , expectedStatusCodeNewDashboard, newDashboardStatusCode);
                Allure.step(stepTwo);
                Assertions.assertEquals(expectedStatusCodeNewDashboard, newDashboardStatusCode);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
