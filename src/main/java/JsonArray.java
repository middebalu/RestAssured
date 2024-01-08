package main.java;




import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import org.assertj.core.error.uri.ShouldHaveUserInfo;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonArray {
    public static void main(String[] args) {
        String j = "{\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"Name\": \"Choc Cake\",\n" +
                "            \"Image\": \"1.jpg\",\n" +
                "            \"Category\": \"Meal\",\n" +
                "            \"Method\": \"\",\n" +
                "            \"Ingredients\": [\n" +
                "                {\n" +
                "                    \"name\": \"1 Cup Ice\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"1 Bag Beans\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"Name\": \"Ice Cake\",\n" +
                "            \"Image\": \"dfdsfdsfsdfdfdsf.jpg\",\n" +
                "            \"Category\": \"Meal\",\n" +
                "            \"Method\": \"\",\n" +
                "            \"Ingredients\": [\n" +
                "                {\n" +
                "                    \"name\": \"1 Cup Ice\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        JsonPath json = new JsonPath(j);
        List<LinkedHashMap<String, Object>> jsonArray = json.getJsonObject("data");
        json.prettyPrint();
        for (LinkedHashMap<String, Object> e : jsonArray) {
            for (Map.Entry<String, Object> entry : e.entrySet()) {
                System.out.println(entry.getKey()+"  "+entry.getValue().getClass());
                if (entry.getValue().equals("Ice Cake")) {
                    System.out.println(e);
                }
            }
        }
        Response response = null;
        JsonPathConfig jo= new JsonPathConfig(j);
        System.out.println(jo.numberReturnType());
        JSONObject j1= new JSONObject(j);
        for(int i=0;i<j1.getJSONArray("data").length();i++){
           JSONObject temp= j1.getJSONArray("data").getJSONObject(i);
            for (Object o : temp.getJSONArray("Ingredients")) {
                JSONObject json1 = (JSONObject) o;
                if (json1.getString("name").equals("1 Bag Beans")) {
                    System.out.println(temp);
                }
            }
        }



    }
}
