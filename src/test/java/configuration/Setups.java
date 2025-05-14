package configuration;

import apiTests.pojos.DashboardPojo;
import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Setups {

    public static final String LOGIN = "default";
    public static final String PASSWORD = "1q2w3e";
    public static final String WIDGET_TYPE = "Launch statistics chart";
    public static final String BASE_URL = "https://demo.reportportal.io";
    public static final String BASE_PATH = "api/v1/default_personal";


    public static RequestSpecification requestSpec(DashboardPojo body) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(BASE_PATH)
                .setBody(body)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", getToken())
                .build();
    }


    private static String getToken() {
        String basePath = "uat/sso/oauth";
        String request = "/token";
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(basePath)
                .addHeader("Authorization", "Basic dWk6dWltYW4=")
                .setConfig(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .encodeContentTypeAs("application/x-www-form-urlencoded", ContentType.TEXT)))
                .addParam("grant_type", "password")
                .addParam("username", LOGIN)
                .addParam("password", PASSWORD)
                .build();
        return requestLoop(specification, request);
    }

    private static String requestLoop(RequestSpecification specification, String request) {
        String token;
        String tokenType;
        while (true) {
            try {
                ValidatableResponse response = RestAssured
                        .given()
                        .spec(specification)
                        .when().post(request)
                        .then().statusCode(200);
                token = response.extract().jsonPath().getString("access_token");
                tokenType = response.extract().jsonPath().getString("token_type");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return tokenType + " " + token;
    }


    public static EventFiringWebDriver driver() {
        String driverPath = "src/test/resourses/chromedriver-win32/chromedriver.exe";
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", Map.of(
                "profile.password_manager_leak_detection", false));
        System.setProperty("webdriver.chrome.driver"
                , driverPath);
        EventFiringWebDriver driver = new EventFiringWebDriver(new ChromeDriver(options));
        driver.get("https://demo.reportportal.io/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static String randomString() {
        byte[] bytes = new byte[10];
        new Random().nextBytes(bytes);
        return new String(bytes, Charset.forName("UTF-8"));
    }

    @Attachment(value = "Click to see screenshot", type = "image/png")
    public static byte[] makeScreenshot(EventFiringWebDriver driver) {
        return driver.getScreenshotAs(OutputType.BYTES);
    }

    public static String stepName(int variation, DashboardPojo dashboard, DashboardPojo newDashboard
            , String dashboardId, int expectedStatusCode, int currentStatusCode) {
        switch (variation) {
            case 1:
                return "Request sending with dasboard parameters: " + dashboard.toString();
            case 2:
                return "Getting of dashboard id and status code: "
                        .concat(System.lineSeparator())
                        .concat("Id: " + dashboardId)
                        .concat(System.lineSeparator())
                        .concat("Status code: " + currentStatusCode);
            case 3:
                return "Status code assertion"
                        .concat(System.lineSeparator())
                        .concat("Expected status code: " + expectedStatusCode)
                        .concat(System.lineSeparator())
                        .concat("Current status code: " + currentStatusCode);
            case 4:
                return "Getting of new dashboard";
            case 5:
                return "Checking of received dashboard and status code"
                        .concat(System.lineSeparator())
                        .concat("Expected dashboard: " + dashboard.toString())
                        .concat(System.lineSeparator())
                        .concat("Received dashboard: " + newDashboard.toString())
                        .concat(System.lineSeparator())
                        .concat("Expected status code: " + expectedStatusCode)
                        .concat(System.lineSeparator())
                        .concat("Current status code: " + currentStatusCode);
        }
        return "";
    }
}
