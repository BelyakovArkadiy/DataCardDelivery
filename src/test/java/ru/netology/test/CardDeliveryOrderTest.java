package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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



    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestAllFields() {

        $("[data-test-id=city] [placeholder='Город']").
                setValue(TestData.getCity());
        $("[data-test-id=date] [placeholder='Дата встречи']").
                sendKeys( Keys.CONTROL +"A",Keys.DELETE);
        $("[data-test-id=date] [placeholder='Дата встречи']").
                setValue(TestData.getDatePlus(5));
        $("[data-test-id=name] [name='name']").
                setValue(TestData.enterName());
        $("[data-test-id=phone] [name='phone']").
                setValue(TestData.enterPhone());
        $("[class=checkbox__box]").click();
        $(withText("Запланировать")).click();
        $(withText("Встреча успешно запланирована на")).
                shouldBe(visible, Duration.ofSeconds(15));
        $(withText(TestData.getDatePlus(5))).
                shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=date] [placeholder='Дата встречи']").
                sendKeys( Keys.CONTROL +"A",Keys.DELETE);
        $("[data-test-id=date] [placeholder='Дата встречи']").
                setValue(TestData.getDatePlus(7));
        $(withText("Запланировать")).click();
        $(withText("Перепланировать")).
                shouldBe(visible, Duration.ofSeconds(3)).click();
        $(withText("Встреча успешно запланирована на")).
                shouldBe(visible, Duration.ofSeconds(15));
        $(withText(TestData.getDatePlus(7))).
                shouldBe(visible, Duration.ofSeconds(15));

    }
}
