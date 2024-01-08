package main.java.com.petstore.api.pojo.deserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.path.json.JsonPath;
import main.java.com.petstore.api.pojo.AddPet;
import main.java.com.petstore.api.pojo.Category;
import main.java.com.petstore.api.pojo.Tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.List.*;

public class Deserialization {

    public static void main(String[] args) throws JsonProcessingException {
        AddPet addPet= new AddPet();
        Category category= new Category();
        Tags tags= new Tags();
        category.setId(12);
        category.setName("local");
        tags.setId(1);
        tags.setName("best");
        List<Tags> tagsList= new ArrayList<>();
        tagsList.add(tags);
        addPet.setId(123);
        addPet.setName("test");
        //addPet.setCategory(category);
        addPet.setTags(tagsList);
        addPet.setPhotoUrl(Arrays.asList("test","test123"));
        addPet.setStatus("fail");
        ObjectMapper objectMapper= new ObjectMapper();
        String addPerReqeust=objectMapper.writeValueAsString(addPet);
        System.out.println(addPerReqeust);
        JsonPath jsonPath= new JsonPath(addPerReqeust);
        jsonPath.prettyPrint();

        AddPet add= objectMapper.readValue(addPerReqeust,AddPet.class);
        System.out.println(add);

    }
}
