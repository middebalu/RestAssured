package test.java.schemavalidation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test.java.pojo.AddPlaceRequest;
import io.restassured.module.jsv.JsonSchemaValidator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
public class JsonSchemaValidation {

    @Test
    public void jsonSchemaValidation() {
        Map<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("id",1);
        bodyMap.put("username","Balaji");
        bodyMap.put("firstName","Kumar");
        bodyMap.put("lastName","midde");
        bodyMap.put("email","midde");
        bodyMap.put("password","123456");
        bodyMap.put("phone","9889999");
        bodyMap.put("userStatus",1);

        File file= new File("C:\\Users\\midde\\IdeaProjects\\RestAssured\\src\\test\\java\\schemavalidation\\Schema");
        Response response=RestAssured.given().log().all().baseUri("https://petstore.swagger.io/v2").contentType(ContentType.JSON).body(bodyMap).
                log().all().when().post("/user");
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(file));
    }
}
