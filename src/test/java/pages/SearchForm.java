package pages;

import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SearchForm {

    @Step("Нажать на кнопку Найти")
    public SearchForm clickSubmitButton() {
        waitUntilFormIsLoaded();
        $(".header-filter__form").$("button[type=submit]").click();

        return this;
    }

    @Step("Выбрать категорию отеля")
    public SearchForm selectHotelCategory() {
        waitUntilFormIsLoaded();
        //select hotel category
        $("[formcontrolname=hotelCategoryIds]").$(".options__option").$(".checkbox").click();

        return this;
    }

    @Step("Выбрать период заезда {dateFrom}-{dateUntil}")
    public SearchForm selectDates(String dateFrom, String dateUntil) {
        waitUntilFormIsLoaded();
        //select dates
        $("[formcontrolname=dates]").click();
        $(".sap-datepick-content").$(byText("сентябрь 2021")).sibling(0).$(byText(dateFrom)).click();
        $(".sap-datepick-content").$(byText("сентябрь 2021")).sibling(0).$(byText(dateUntil)).click();

        return this;
    }

    @Step("Выбрать регион/город {region}")
    public SearchForm selectRegion(String region) {
        waitUntilFormIsLoaded();
        //select region/city
        $("[formcontrolname=region]").$(byText(region)).click();

        return this;
    }

    @Step("Выбрать возраст детей/ребенка {childrenAge}")
    public SearchForm selectChildrenAge(String childrenAge) {
        waitUntilFormIsLoaded();
        //child age
        $(".selectChildrenAge").click();
        $(".dropdown-options").$(byText(childrenAge)).click();

        return this;
    }

    @Step("Выбрать количество детей {children}")
    public SearchForm selectNumberOfChildren(String children) {
        //child
        $(".tourists-children").sibling(0).click();
        $(".dropdown-options").$(byText(children)).click();

        return this;
    }

    @Step("Выбрать количество взрослых {adults}")
    public SearchForm selectNumberOFAdults(String adults) {
        //select number of passengers
        //adults
        $("[formcontrolname=adults]").click();
        $(".dropdown-options").$(byText(adults)).click();

        return this;
    }

    @Step("Выбрать страну {country}")
    public SearchForm selectDestinationCountry(String country) {
        //select destination country
        $(byName("destCountry")).click();
        $(".dropdown-options").$(byText(country)).click();

        return this;
    }

    @Step("Выбрать город отправления {city}")
    public SearchForm selectDepartureCity(String city) {
        //select departure city
        $("[formcontrolname=startCity]").click();
        $(".dropdown-options").$(byText(city)).click();

        return this;
    }

    private void waitUntilFormIsLoaded() {
        $("[selectedlistlabel='Выбранное питание'] .options").shouldBe(visible, Duration.ofSeconds(8));
        $("[selectedlistlabel='Выбранные категории'] .options").shouldBe(visible, Duration.ofSeconds(8));
        $("[selectedlistlabel='Выбранные гостиницы'] .options").shouldBe(visible, Duration.ofSeconds(8));
    }
}