package ru.netology.web;

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


    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

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
