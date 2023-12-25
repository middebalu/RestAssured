package test.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListWordRepeations {
    public static void main(String[] args){
        List<String> names= new ArrayList<>();
        names.add("balaji");
        names.add("sneha");
        names.add("balaji");
        names.add("kumar");
        names.add("balaji");
        names.add("kumar");
        names.add("balaji");
        Map<String ,Integer> count=new HashMap<>();
        for(String name:names){
            if(count.containsKey(name)){
                count.put(name,count.get(name)+1);

            } else {
                count.put(name,1);
            }
            count.put("krishna",123);

        }

        System.out.println(count.size());

    }
}
