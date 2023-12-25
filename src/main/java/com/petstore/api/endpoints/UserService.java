package main.java.com.petstore.api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserService {

    public static Response createUser(User payload){
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(payload).baseUri(Endpoints.base_Uri)
                .when().post(Endpoints.userService);
    }
}
