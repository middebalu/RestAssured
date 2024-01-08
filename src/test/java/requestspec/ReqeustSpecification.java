package test.java.requestspec;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import static org.hamcrest.Matchers.*;

public class ReqeustSpecification {
    @Test
    public void requestSpecification(){
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
        Faker faker= new Faker();

        Map<String, Object> bodyMap = new LinkedHashMap<>();
        //bodyMap.put("id",1);
        bodyMap.put("username",fakeValuesService.letterify("?????"));
        bodyMap.put("firstName",fakeValuesService.letterify("?????"));
        bodyMap.put("lastName",fakeValuesService.letterify("?????"));
        bodyMap.put("email",fakeValuesService.letterify("??????test@gmail.com"));
        bodyMap.put("password",faker.internet().password());
        bodyMap.put("phone",faker.bothify("##########"));
        bodyMap.put("userStatus",1);
        RequestSpecification res= new RequestSpecBuilder().setAccept(ContentType.JSON).setContentType(ContentType.JSON)
                .setBaseUri("https://petstore.swagger.io/v2").setBody(bodyMap).log(LogDetail.BODY).build();
        RestAssured.given().spec(res).when().post("/user").then().log().all().statusCode(200).assertThat().body("code",equalTo(200));

    }
}
