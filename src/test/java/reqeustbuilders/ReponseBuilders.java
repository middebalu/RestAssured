package test.java.reqeustbuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import main.java.com.petstore.api.pojo.user.UserResponse;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class ReponseBuilders {
    @Test
    public void requestSpecification() throws JsonProcessingException {
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
        Response response1 = RestAssured
                .given()
                .spec(res)
                .when()
                .post("/user");
        // converting json to pojo( de-serialization)
        UserResponse userResponse= response1.body().as(UserResponse.class);
        ObjectMapper objectMapper= new ObjectMapper();
        // converting pojo to json( serialization) with object mapper
        String s=objectMapper.writeValueAsString(userResponse);
        // converting json to pojo( de-serialization) with object mapper
        UserResponse userResponse1=objectMapper.readValue(s,UserResponse.class);
        System.out.println(response1.body().asString());

        Response responseBuilder = new ResponseBuilder().clone(response1).setStatusCode(400).build();
        responseBuilder.then().statusCode(400);
    }
}
