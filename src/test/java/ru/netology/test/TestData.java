package ru.netology.test;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


@Data
@Value

public class TestData {

    private static Faker faker = new Faker(new Locale("ru"));


    public static String getCity(){
        String[] city = { "Москва", "Кемерово", "Смоленск", "Тамбов", "Казань", "Махачкала", "Курган"};
        int rnd = new Random().nextInt(city.length);
        return city[rnd];
    }
    public static String getDatePlus(int plus) {
        return LocalDate.now().plusDays(plus).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }


        public static String enterName(){
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String name = firstName + " " + lastName;
        return name;
    }

    public static String enterPhone()  {
        return  faker.numerify("+7##########");

    }

    private TestData() {
    }
}



