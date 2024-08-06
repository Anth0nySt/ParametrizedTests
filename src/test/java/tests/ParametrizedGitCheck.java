package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.data.Features;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedGitCheck {

    @BeforeEach
    void openBrowser() {
        open("https://github.com/");
    }


    @ValueSource(strings = {
            "Actions", "Packages"
    })
    @ParameterizedTest
    @DisplayName("Check opened page")
    void headerMenuCheck(String chapter) {
        $(".HeaderMenu-nav").$("li").hover();
        $("div").$(byText(chapter)).click();
        $("main").shouldHave(text("Features"));
    }


    @CsvSource (value = {
            "Actions, GitHub Actions",
            "Packages, GitHub Packages"
    })
    @ParameterizedTest
    @DisplayName("Check titles")
    void headerMenuTitlesCheck(String chapter, String title) {
        $(".HeaderMenu-nav").$("li").hover();
        $("div").$(byText(chapter)).click();
        $("main").shouldHave(text(title));
    }

    @EnumSource(Features.class)
    @ParameterizedTest
    @DisplayName("Checked chapters")
    void headerMenuCheckEnums(Features chapters) {
        $(".HeaderMenu-nav").$("li").hover();
        $("div").shouldHave(text(chapters.name()));
    }
}
