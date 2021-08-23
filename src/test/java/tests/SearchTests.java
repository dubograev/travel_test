package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.SearchForm;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchTests {

    SearchForm searchForm = new SearchForm();

    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void searchForHotelsTest() {

        open("https://intourist.ru/search/");
        if ($(".more-products").exists()) {
            $(byText("Понятно")).click();
        }

        searchForm.selectDepartureCity("Москва")
                .selectDestinationCountry("Турция")
                .selectNumberOFAdults("2")
                .selectNumberOfChildren("1")
                .selectChildrenAge("9")
                .selectRegion("Мармарис")
                .selectDates("1", "2")
                .selectHotelCategory()
                .clickSubmitButton();

        $(byText("Откуда")).sibling(0).shouldHave(text("Москва"));
        $(byText("Куда")).sibling(0).shouldHave(text(" Турция (С перелётом) "));
        $(byText("Туристы")).sibling(0).shouldHave(text("2 взр + 1 реб"));
        $$(".sap-hotel-card").shouldHave(sizeGreaterThan(0));
    }

}