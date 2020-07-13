package triangle.core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.util.HashMap;

import static org.hamcrest.Matchers.lessThan;
import static triangle.utils.Constants.*;

public class TriangleApi {

    private TriangleApi() {}
    private HashMap<String, String> params = new HashMap<String, String>();
    private String ids;
    private HashMap<String, String> queryParams = new HashMap<>();

    public static class ApiBuilder {
        TriangleApi triangleApi;

        private ApiBuilder(TriangleApi api) {
            triangleApi = api;
        }

        public ApiBuilder separator(String text) {
            triangleApi.params.put("separator", text);
            return this;
        }

        public ApiBuilder input(String text) {
            triangleApi.params.put("input", text);
            return this;
        }

        public ApiBuilder id(String text) {
            triangleApi.ids = text;
            return this;
        }

        public Response callApiCreate() {
            return RestAssured.given()
                    .spec(baseRequestConf())
                    .body(triangleApi.params)
                    .log().all()
                    .post(REQ_CREATE_TRINGLE);
        }

        public Response callApiAll() {
            return RestAssured.given()
                    .spec(baseRequestConf())
                    .log().all()
                    .get(REQ_ALL_TRINGLE);
        }

        public Response callApiDelete() {
            return RestAssured.given()
                    .spec(baseRequestConf())
                    .log().all()
                    .delete(String.format(REQ_DELETE_TRINGLE, triangleApi.ids));
        }

        public Response callApiPerimeter() {
            return RestAssured.given()
                    .spec(baseRequestConf())
                    .log().all()
                    .get(String.format(REQ_PERIMETER_TRINGLE, triangleApi.ids));
        }

        public Response callApiArea() {
            return RestAssured.given()
                    .spec(baseRequestConf())
                    .log().all()
                    .get(String.format(REQ_AREA_TRINGLE, triangleApi.ids));
        }
    }

    public static ApiBuilder with() {
        TriangleApi api = new TriangleApi();
        return new ApiBuilder(api);
    }

    public static ResponseSpecification succesResponse() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static RequestSpecification baseRequestConf() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.ANY.JSON)
                .addHeader("X-User", "d893cc65-3339-452a-afaf-88a26e6c0f72")
                .build();
    }
}
