package test.java;

import java.util.ArrayList;
import java.util.List;

public class Excer {
    public static void main(String[] args){
        List<String> colorsList= new ArrayList<>();
        colorsList.add("red");
        colorsList.add("blue");
        colorsList.add("yellow");
        for(String e:colorsList){
            System.out.println(e);
        }

    }
}
