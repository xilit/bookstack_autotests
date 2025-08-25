package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.TestConfig;
import extensions.AllureSelenideExtension;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.LoginTests;
import utils.ConfigReader;

@ExtendWith(AllureSelenideExtension.class)
public class UserLoginTests {
    LoginTests loginTests = new LoginTests();

    @BeforeAll
    static void setup() {
        TestConfig.setup();
    }

    @BeforeEach
    void openLoginPage() {
        loginTests.openPage();
    }

    @Test
    @DisplayName("Вход с валидными данными")
    @Feature("Авторизация")
    @Story("Успешный вход")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "http://194.87.222.160/")
    void successLoginWithValidCredentials() {
        String email = ConfigReader.get("user.email");
        String password = ConfigReader.get("user.password");

        loginTests.setEmail(email)
                .setPassword(password)
                .pressEnter()
                .verifyResultWhenLoginValid();
    }

    @Test
    @DisplayName("Вход без пароля")
    @Feature("Авторизация")
    @Story("Ошибки входа")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Testing", url = "http://194.87.222.160/")
    void shouldDisplayErrorMessageWhenLoginWithEmptyPassword() {
        String email = ConfigReader.get("user.email");

        loginTests.setEmail(email)
                .pressEnter()
                .verifyMessageAboutEmptyPassword();
    }

    @Test
    @DisplayName("Вход без email")
    @Feature("Авторизация")
    @Story("Ошибки входа")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Testing", url = "http://194.87.222.160/")
    void shouldDisplayErrorMessageWhenLoginWithEmptyEmail() {
        loginTests.pressEnter()
                .verifyMessageEmailRequiredField();
    }

    @Test
    @DisplayName("Ввод некорректного email")
    @Feature("Авторизация")
    @Story("Ошибки входа")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Testing", url = "http://194.87.222.160/")
    void verifyEmailValidationError() {
        String email = ConfigReader.get("incorrect.email");
        loginTests.setEmail(email)
                .pressEnter()
                .verifyEmailValidationError();

    }
}

