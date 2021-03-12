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
@AllArgsConstructor
@NoArgsConstructor
public class TestData {

    Faker faker = new Faker(new Locale("ru"));


    public static String getCity(){
        String[] city = { "Москва", "Кемерово", "Смоленск", "Тамбов", "Казань", "Махачкала", "Курган"};
        int rnd = new Random().nextInt(city.length);
        return city[rnd];
    }
    public static String getDatePlusFive(){
        LocalDate date = LocalDate.now();
        LocalDate dateDelivery = date.plusDays(5);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dataText = dateDelivery.format(formatters);
        return dataText;
    }

    public static String getDatePlusSeven(){
        LocalDate date = LocalDate.now();
        LocalDate dateDelivery = date.plusDays(7);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dataText = dateDelivery.format(formatters);
        return dataText;
    }


    public static String enterName(Faker faker){
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String name = firstName + " " + lastName;
        return name;
    }

    public static String enterPhone(Faker faker)  {
        String phone = faker.numerify("+7##########");
        return phone;
    }

}



