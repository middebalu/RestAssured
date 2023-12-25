package test.java.schemavalidation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test.java.pojo.AddPlaceRequest;
import io.restassured.module.jsv.JsonSchemaValidator;
import java.util.HashMap;
import java.util.Map;
public class JsonSchemaValidation {

    @Test
    public void jsonSchemaValidation() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("key", "qaclick123");
        String baseUri = "https://rahulshettyacademy.com/";
        AddPlaceRequest addPlaceRequest = new AddPlaceRequest();
        addPlaceRequest.setAccuracy(52);
        addPlaceRequest.setAddress("Frontline house, ");
        addPlaceRequest.setPhone_number("(+91) 983 893 3937");
        addPlaceRequest.setWebsite("http://google.com");
        addPlaceRequest.setLanguage("French-IN");
        addPlaceRequest.setName("balajikumar");

        Response res=RestAssured.given().log().all().baseUri(baseUri).queryParams(params).contentType(ContentType.JSON).body(addPlaceRequest).
                expect().defaultParser(Parser.JSON).log().all().when().post("/maps/api/place/add/json").then().extract().response();
        System.out.println(res.body().prettyPrint());
    }
}
