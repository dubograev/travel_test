package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPageTests {

    @AfterEach
    void tearDown() {
        Selenide.closeWindow();
    }

    @Test
    void sightseeingToursInRussiaTitleTest() {
        open("https://intourist.ru/");
        $(".sightseeingtours").shouldHave(text("Экскурсионные туры по России"));
    }

    @Test
    void sightseeingToursInRussiaCardsShowMoreTest() {
        open("https://intourist.ru/");
        $(".sightseeingtours_bt-btn").shouldBe(visible);
        $("#excursionToursShowMore").click();
        $(".sightseeingtours_bt-btn").shouldNotBe(visible);
        $(".sightseeingtours").$$(".sightseeingtours__slide").shouldHave(size(12));
    }
}