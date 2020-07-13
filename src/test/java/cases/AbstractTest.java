package cases;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import triangle.core.TriangleApi;

import java.util.List;

import static utils.Constants.ID_FIELD;

public class AbstractTest {

    @BeforeTest
    public void setFilter() {
        RestAssured.filters(new AllureRestAssured());
    }

    @AfterSuite
    public void tear_down() {
        List<String> ids =
                TriangleApi.with()
                        .callApiAll()
                        .then()
                        .assertThat()
                        .log().body()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .path(ID_FIELD);

        ids.stream().forEach(s ->
                TriangleApi.with()
                        .id(s)
                        .callApiDelete()
                        .then()
                        .assertThat()
                        .log().body()
                        .statusCode(HttpStatus.SC_OK)
        );
    }
}
