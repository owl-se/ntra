package cases;

import models.TriangleData;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import triangle.core.TriangleApi;

import utils.Calculation;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsNull.notNullValue;
import static utils.Constants.BODY_RESPONSE_RESULT;
import static utils.Constants.ID_FIELD;

public class BaseTest extends AbstractTest{
    TriangleData testTriangle;

    /**
     * Scenario: Verify it's possible to create equilateral triangle
     */
    @Test
    public void tc_base_01_create_equilateral_triangle() {
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

    /**
     * Scenario: Verify it's possible to view created triangle
     */
    @Test(dependsOnMethods = { "tc_base_01_create_equilateral_triangle" })
    public void tc_base_02_view_created_triangle() {
        TriangleApi.with()
                .callApiAll()
                .then()
                .assertThat()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("id[0]", containsString(testTriangle.getId()));
    }

    /**
     * Scenario: Verify perimeter calculation for triangle
     */
    @Test(dependsOnMethods = { "tc_base_01_create_equilateral_triangle" })
    public void tc_base_03_get_triangle_perimeter() {
        float p =
                TriangleApi.with()
                .id(testTriangle.getId())
                .callApiPerimeter()
                .then()
                .assertThat()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path(BODY_RESPONSE_RESULT);

        Assert.assertEquals(p, Calculation.getPerimeter(testTriangle.getFirstSide(), testTriangle.getSecondSide()
                , testTriangle.getThirdSide()),
                "Perimeter calculated incorrectly");
    }

    /**
     * Scenario: Verify area calculation for equilateral triangle
     */
    @Test(dependsOnMethods = { "tc_base_01_create_equilateral_triangle" })
    public void tc_base_04_get_triangle_area() {
        float s = TriangleApi.with()
                .id(testTriangle.getId())
                .callApiArea()
                .then()
                .assertThat()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path(BODY_RESPONSE_RESULT);

        Assert.assertEquals(s, Calculation.getArea(testTriangle.getFirstSide(), testTriangle.getSecondSide(),
                testTriangle.getThirdSide(), true),
                "Area calculated incorrectly");
    }

    /**
     * Scenario: Verify it's possible to delete a triangle
     */
    @Test(dependsOnMethods = { "tc_base_01_create_equilateral_triangle", "tc_base_02_view_created_triangle"})
    public void tc_base_05_delete_triangle() {
        TriangleApi.with()
                .id(testTriangle.getId())
                .callApiDelete()
                .then()
                .assertThat()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }


}
