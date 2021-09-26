package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.SearchForm;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase{

    SearchForm searchForm = new SearchForm();

    @Test
    void searchForHotelsTest() {

        open("https://intourist.ru/search/");
        if ($(".more-products").exists()) {
            $(byText("Понятно")).click();
        }

        step("Заполнить форму и нажать кнопку Найти", () -> {
            searchForm.selectDepartureCity("Москва")
                    .selectDestinationCountry("Турция")
                    .selectNumberOFAdults("2")
                    .selectNumberOfChildren("1")
                    .selectChildrenAge("9")
                    .selectRegion("Мармарис")
                    .selectDates("1", "2")
                    .selectHotelCategory()
                    .clickSubmitButton();
        });

        step("Проверяем, что поле Откуда содержит город Москва", () -> {
            $(byText("Откуда")).sibling(0).shouldHave(text("Москва"));
        });

        step("Проверяем, что поле Куда содержит страну Турция", () -> {
        $(byText("Куда")).sibling(0).shouldHave(text(" Турция (С перелётом) "));
        });

        step("Проверяем, что поле Туристы содержит 2 взр + 1 реб", () -> {
        $(byText("Туристы")).sibling(0).shouldHave(text("2 взр + 1 реб"));
        });

        step("Проверяем, что страница содержит результаты поиска", () -> {
        $$(".sap-hotel-card").shouldHave(sizeGreaterThan(0));
        });
    }

}