package test.java.apimethods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import test.java.pojo.AddPlaceRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PlaceApi {
    String baseUri="https://rahulshettyacademy.com/";

    public Response addPlace(AddPlaceRequest addPlaceRequest){
        return RestAssured.given().log().all().queryParam("key","qaclick123").contentType(ContentType.JSON).baseUri(baseUri).body(addPlaceRequest).expect().defaultParser(Parser.JSON).log().all().when().post("/maps/api/place/add/json");
    }

    /*public Response getPlace(){
        re
    }
    public Response updatePlace(){

    }
    public Response deletePlace(){

    }*/
}
