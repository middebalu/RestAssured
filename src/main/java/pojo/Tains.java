package main.java.pojo;

import java.util.Random;

public class Tains {
    public static void main(String args[]){
        Random ran= new Random();
        for (int s=0;s<100;s++
             ) {
            int x= ran.nextInt(5);
            System.out.println(x);
        }

    }
}
