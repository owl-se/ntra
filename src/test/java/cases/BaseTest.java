package cases;

import models.TriangleData;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import triangle.core.TriangleApi;

import static org.hamcrest.core.IsNull.notNullValue;
import static utils.Constants.ID_FIELD;

public class BaseTest extends AbstractTest{
    TriangleData testTriangle;

    @Test
    public void testCreate() {
        testTriangle =
                TriangleApi.with()
                .separator("-")
                .input("5-5-5")
                .callApiCreate()
                .then()
                .assertThat()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(ID_FIELD, notNullValue())
                .extract()
                .body().as(TriangleData.class);
    }
}
