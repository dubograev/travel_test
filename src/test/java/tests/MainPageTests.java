package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPageTests extends TestBase{

    @AfterEach
    void tearDown() {
        annotatedAttachment();
        Selenide.closeWindow();
    }

    @Test
    @Feature("Main Page")
    @Owner("Andrey")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверить, что главная страница содержит секцию 'Экскурсионные туры по России'")
    void sightseeingToursInRussiaTitleTest() {
        open("https://intourist.ru/");
        $(".sightseeingtours").shouldHave(text("Экскурсионные туры по России"));
    }

    @Test
    @Feature("Main Page")
    @Owner("Andrey")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверить, кнопка 'Показать еще' в секции 'Экскурсионные туры по России' работает")
    void sightseeingToursInRussiaCardsShowMoreTest() {
        open("https://intourist.ru/");
        $(".sightseeingtours_bt-btn").shouldBe(visible);
        $("#excursionToursShowMore").click();
        $(".sightseeingtours_bt-btn").shouldNotBe(visible);
        $(".sightseeingtours").$$(".sightseeingtours__slide").shouldHave(size(12));
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] annotatedAttachment() {
        return Selenide.screenshot(OutputType.BYTES);
    }
}