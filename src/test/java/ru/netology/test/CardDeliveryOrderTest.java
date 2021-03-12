package ru.netology.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryOrderTest {
    TestData testData = new TestData();
    Faker faker = new Faker(new Locale("ru"));

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void shouldTestAllFields() {

        $("[data-test-id=city] [placeholder='Город']").
                setValue(testData.getCity());
        $("[data-test-id=date] [placeholder='Дата встречи']").
                sendKeys( Keys.CONTROL +"A",Keys.DELETE);
        $("[data-test-id=date] [placeholder='Дата встречи']").
                setValue(testData.getDatePlusFive());
        $("[data-test-id=name] [name='name']").
                setValue(testData.enterName(faker));
        $("[data-test-id=phone] [name='phone']").
                setValue(testData.enterPhone(faker));
        $("[class=checkbox__box]").click();
        $(withText("Запланировать")).click();
        $(withText("Встреча успешно запланирована на")).
                shouldBe(visible, Duration.ofSeconds(15));
        $(withText(testData.getDatePlusFive())).
                shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=date] [placeholder='Дата встречи']").
                sendKeys( Keys.CONTROL +"A",Keys.DELETE);
        $("[data-test-id=date] [placeholder='Дата встречи']").
                setValue(testData.getDatePlusSeven());
        $(withText("Запланировать")).click();
        $(withText("Перепланировать")).
                shouldBe(visible, Duration.ofSeconds(3)).click();
        $(withText("Встреча успешно запланирована на")).
                shouldBe(visible, Duration.ofSeconds(15));
        $(withText(testData.getDatePlusSeven())).
                shouldBe(visible, Duration.ofSeconds(15));

    }
}
