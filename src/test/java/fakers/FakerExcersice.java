package test.java.fakers;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.testng.annotations.Test;

import java.util.Locale;

public class FakerExcersice {

    @Test
    public void generateGmail() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
        //? replace with digits # with number
        for (int i = 0; i < 10; i++) {
            System.out.println(fakeValuesService.bothify("????##test@gmail.com"));
        }
    }

}
