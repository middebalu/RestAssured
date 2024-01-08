package test.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import test.java.pojo.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

public class EndToEndScenario {
    public static void main(String[] args) throws JsonProcessingException {


            AddPlaceRequest addPlaceRequest = new AddPlaceRequest();
            addPlaceRequest.setAccuracy(50);
            addPlaceRequest.setAddress("Frontline house, ");
            addPlaceRequest.setPhone_number("(+91) 983 893 3937");
            addPlaceRequest.setWebsite("http://google.com");
            addPlaceRequest.setLanguage("French-IN");
            addPlaceRequest.setName("balaji");
            Location location = new Location();
            location.setLatitude(-38.383494);
            location.setLongitude(34.427362);
            addPlaceRequest.setLocation(location);
            List<String> typesList= Arrays.asList("home","office");
            addPlaceRequest.setTypes(typesList);
            Map<String, String> params = new HashMap<String, String>();
            params.put("key", "qaclick123");
            String baseUri = "https://rahulshettyacademy.com/";
            System.out.println("********************************* add place *****************");
            ObjectMapper objectMapper= new ObjectMapper();

            String str=objectMapper.writeValueAsString(addPlaceRequest);
            System.out.println(str);
           //********************* Request builder **********************************
            AddPlaceRequest adObj= objectMapper.readValue(str, AddPlaceRequest.class);
            RequestSpecification req=  new RequestSpecBuilder().addQueryParams(params).setBaseUri(baseUri).setContentType(ContentType.JSON).build();

           //********************* Response builder **********************************

            ResponseSpecification res= new ResponseSpecBuilder().expectStatusCode(200).build();
            Response response = RestAssured.given().log().all().spec(req).body(addPlaceRequest).expect().defaultParser(Parser.JSON).log().all().when().post("/maps/api/place/add/json").then().spec(res).extract().response();
            //assertThat(response.getStatusCode()).isEqualTo(200);
            System.out.println("Status code "+response.getStatusCode());
            System.out.println("Status code "+response.getBody().asString());
            AddPlaceResponse addPlaceResponse = response.getBody().as(AddPlaceResponse.class);
             objectMapper.readValue(response.getBody().toString(),AddPlaceResponse.class);
            params.put("place_id", addPlaceResponse.getPlace_id());
            response = RestAssured
                    .given()
                        .spec(req)
                        .expect()
                        .defaultParser(Parser.JSON)
                    .   log().all()
                    .when()
                        .get("/maps/api/place/get/json")
                    .then()
                        .spec(res).extract().response();
            JsonPath json1 = new JsonPath(response.getBody().asString());
           // Assert.assertEquals(json1.get("location.latitude"), addPlaceRequest.getLocation().getLatitude());


            System.out.println("********************************* update place id");
            UpdatePlaceRequest updatePlaceRequest = new UpdatePlaceRequest();
            updatePlaceRequest.setPlace_id(addPlaceResponse.getPlace_id());
            updatePlaceRequest.setAddress("80 winter walk,");
            updatePlaceRequest.setTypes("shop");
            updatePlaceRequest.setKey("qaclick123");
            response = RestAssured.given().spec(req).body(updatePlaceRequest).log().all().when().put("/maps/api/place/update/json");
            Assert.assertEquals(response.getBody().jsonPath().getString("msg"), "Address successfully updated");

            response = RestAssured.given().spec(req).log().all().when().get("/maps/api/place/get/json");
            System.out.println(response.getBody().asString());
           // Assert.assertEquals(response.getBody().jsonPath().getString("address"), updatePlaceRequest.getAddress());
            params.remove("place_id");
            System.out.println("********************************* update place id");
            DeletePlaceRequest deletePlaceRequest = new DeletePlaceRequest();
            deletePlaceRequest.setPlace_id(updatePlaceRequest.getPlace_id());
            response = RestAssured.given().spec(req).body(deletePlaceRequest).expect().defaultParser(Parser.JSON).log().all().when().delete("/maps/api/place/delete/json");
            System.out.println(response.getBody().asString());
            Assert.assertEquals(response.getBody().jsonPath().getString("status"), "OK");
            //******* complex json examples**********
           /* CoursesReqeust coursesReqeust= new CoursesReqeust();
            coursesReqeust.setInstructor("David");
            coursesReqeust.setExpertise("Automation");
            coursesReqeust.setServices("Project");
            coursesReqeust.setUrl("testing.org");
            Courses courses= new Courses();
            WebAutomation webAutomation= new WebAutomation();
            Api api= new Api();
            webAutomation.setCourseTitle("Selenium");
            webAutomation.setPrice(50);
            List<WebAutomation> webAutomationList= new ArrayList<WebAutomation>();
            webAutomationList.add(webAutomation);
            webAutomation.setCourseTitle("cypress");
            webAutomation.setPrice(40);
            webAutomationList.add(webAutomation);
            webAutomation.setPrice(30);
            webAutomation.setCourseTitle("protractor");
            webAutomationList.add(webAutomation);
            api.setCoureTitle("Restaasured");
            api.setPrice(30);
            List<Api> apiList= new ArrayList<Api>();
            apiList.add(api);
            api.setCoureTitle("Postman");
            api.setPrice(20);
            apiList.add(api);
            courses.setApi(apiList);
            courses.setWebAutomation(webAutomationList);
            coursesReqeust.setCourses(courses);
            ObjectMapper objectMapper = new ObjectMapper();

            String jsonreqeust= objectMapper.writeValueAsString(coursesReqeust);
            JsonPath jsonPath= new JsonPath(jsonreqeust);
           CoursesReqeust response1= new CoursesReqeust();
            response1= objectMapper.readValue(jsonreqeust, CoursesReqeust.class);
            System.out.println(jsonPath.get("courses.webAutomation[0].courseTitle"));
            System.out.println(response1.getCourses().getWebAutomation().size());

           System.out.println(jsonreqeust);*/





    }
}
