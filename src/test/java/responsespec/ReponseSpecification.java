package test.java.responsespec;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class ReponseSpecification {
    @Test
    public void requestSpecification() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
        Faker faker = new Faker();

        Map<String, Object> bodyMap = new LinkedHashMap<>();
        //bodyMap.put("id",1);
        bodyMap.put("username", fakeValuesService.letterify("?????"));
        bodyMap.put("firstName", fakeValuesService.letterify("?????"));
        bodyMap.put("lastName", fakeValuesService.letterify("?????"));
        bodyMap.put("email", fakeValuesService.letterify("??????test@gmail.com"));
        bodyMap.put("password", faker.internet().password());
        bodyMap.put("phone", faker.bothify("##########"));
        bodyMap.put("userStatus", 1);
        RequestSpecification res1 = new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setBaseUri("https://petstore.swagger.io/v2").build();
        RequestSpecification res = new RequestSpecBuilder()
                .addRequestSpecification(res1)
                .setBody(bodyMap)
                .log(LogDetail.BODY)

                .build();
        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectBody("code", equalTo(200))
                .log(LogDetail.BODY)
                .build();
        Response responseBuilder1 = new ResponseBuilder().setStatusCode(400).build();
        System.out.println("Status code: " + responseBuilder1.getStatusCode());
        RestAssured
                .given()
                .spec(res)
                .when()
                .post("/user")
                .then()
                .spec(resSpec);


    }
}
