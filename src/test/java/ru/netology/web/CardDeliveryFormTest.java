package ru.netology.web;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.entities.DataInfo;
import ru.netology.utils.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;

public class CardDeliveryFormTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }


    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }


    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Story("Изменение даты доставки карты")
    @Test
    public void shouldChangeDateOfDelivery() {

        DataInfo dataInfo = DataGenerator.DeliveryCardForm.generateInfo("ru");
        String date = DataGenerator.DeliveryCardForm.generateDate();
        String newDate = DataGenerator.DeliveryCardForm.generateDate();

        $("[data-test-id=city] input").val(dataInfo.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").val(date);
        $("[data-test-id=name] input").val(dataInfo.getName());
        $("[data-test-id=phone] input").val(dataInfo.getPhone());
        $("[data-test-id=agreement] span").click();
        $$("button").find(exactText("Запланировать")).click();

        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно запланирована на " + date));

        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").val(newDate);
        $$("button").find(exactText("Запланировать")).click();

        $("[data-test-id=replan-notification] .notification__content").shouldBe(visible)
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $(withText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible)
                .shouldHave(exactText("Встреча успешно запланирована на " + newDate));
    }

}
