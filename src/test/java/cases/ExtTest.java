package cases;

import models.TriangleData;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import triangle.core.TriangleApi;

import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static utils.Constants.*;

public class ExtTest extends AbstractTest{
    TriangleData testTriangle;

    /**
     * Scenario: Verify it's not possible to create triangle with 0 sides length
     */
    @Test(priority = 1)
    public void tc_ext_01_create_triangle_with_zero_sides() {
        TriangleApi.
                with()
                .separator(";")
                .input("0;0;0")
                .callApiCreate()
                .then()
                .assertThat()
                .log().body()
                .statusCode(anyOf(is(HttpStatus.SC_UNPROCESSABLE_ENTITY), is(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE)));
    }

    /**
     * Scenario: Verify it's not possible to create triangle with characters as sides length
     */
    @Test(priority = 2)
    public void tc_ext_02_create_triangle_with_character_as_sides() {
        TriangleApi.
                with()
                .separator(";")
                .input("a;b;c")
                .callApiCreate()
                .then()
                .assertThat()
                .log().body()
                .statusCode(anyOf(is(HttpStatus.SC_UNPROCESSABLE_ENTITY), is(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE)));
    }

    /**
     * Scenario: Verify it's not possible to store more than 10 entities
     */
    @Test(priority = 3)
    public void tc_ext_03_create_more_than_ten_entities() {
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

        IntStream.rangeClosed(1, 10)
                .forEach(i -> TriangleApi.with()
                        .separator(";")
                        .input("3;3;3")
                        .callApiCreate()
                        .then()
                        .assertThat()
                        .log().body()
                        .statusCode(HttpStatus.SC_OK)
                );

        TriangleApi.with()
                .separator("-")
                .input("5-5-5")
                .callApiCreate()
                .then()
                .assertThat()
                .log().body()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY)
                .body(BODY_RESPONSE_MESSAGE, equalTo(BODY_RESPONSE_MESSAGE_LIMIT_EXCEEDED));
    }



}
