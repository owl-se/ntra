package cases;

import models.TriangleData;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import triangle.core.TriangleApi;

import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static utils.Constants.ID_FIELD;

public class ExtTest extends AbstractTest{
    TriangleData testTriangle;

    /**
     * Scenario: Verify it's not possible to create triangle with 0 sides length
     */
    @Test
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
    @Test
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



}
