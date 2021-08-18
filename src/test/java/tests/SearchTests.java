package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchTests {

    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void searchForHotels() {

        open("https://intourist.ru/search/");
        if ($(".more-products").exists()) {
            $(byText("Понятно")).click();
        }

        //select departure city
        $("[formcontrolname=startCity]").click();
        $(".dropdown-options").$(byText("Москва")).click();

        //select destination country
        $(byName("destCountry")).click();
        $(".dropdown-options").$(byText("Турция")).click();

        //select number of passengers
        //adults
        $("[formcontrolname=adults]").click();
        $(".dropdown-options").$(byText("2")).click();
        //child
        $(".tourists-children").sibling(0).click();
        $(".dropdown-options").$(byText("1")).click();

        wainUntilFormIsLoaded();
        //child age
        $(".selectChildrenAge").click();
        $(".dropdown-options").$(byText("9")).click();

        wainUntilFormIsLoaded();
        //select region/city
        $("[formcontrolname=region]").$(byText("Мармарис")).click();

        wainUntilFormIsLoaded();
        //select dates
        $("[formcontrolname=dates]").click();
        $(".sap-datepick-content").$(byText("сентябрь 2021")).sibling(0).$(byText("1")).click();
        $(".sap-datepick-content").$(byText("сентябрь 2021")).sibling(0).$(byText("5")).click();


        wainUntilFormIsLoaded();
        //select hotel category
        $("[formcontrolname=hotelCategoryIds]").$(".options__option").$(".checkbox").click();

        wainUntilFormIsLoaded();
        $(".header-filter__form").$("button[type=submit]").click();

        $(byText("Откуда")).sibling(0).shouldHave(text("Москва"));
        $(byText("Куда")).sibling(0).shouldHave(text(" Турция (С перелётом) "));
        $(byText("Туристы")).sibling(0).shouldHave(text("2 взр + 1 реб"));
        $$(".sap-hotel-card").shouldHave(sizeGreaterThan(0));
    }

    private void wainUntilFormIsLoaded() {
        $("[selectedlistlabel='Выбранное питание'] .options").shouldBe(visible, Duration.ofSeconds(8));
        $("[selectedlistlabel='Выбранные категории'] .options").shouldBe(visible, Duration.ofSeconds(8));
        $("[selectedlistlabel='Выбранные гостиницы'] .options").shouldBe(visible, Duration.ofSeconds(8));
    }
}