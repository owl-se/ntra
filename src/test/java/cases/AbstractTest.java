package cases;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public class AbstractTest {

    @BeforeTest
    public void setFilter() {
        RestAssured.filters(new AllureRestAssured());
    }
}
