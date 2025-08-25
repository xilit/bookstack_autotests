package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class LoginTests {

    private final SelenideElement
            emailInput = $("#email"),
            passwordInput = $("#password"),
            loginButton = $(".button");

    @Step("Открываем страницу авторизации")
    public LoginTests openPage() {
        open("/login");
        $("#login-form").shouldHave(exist);
        return this;
    }

    @Step("Вводим email пользователя")
    public LoginTests setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Вводим пароль пользователя")
    public LoginTests setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Включаем чек-бокс \"Запомнить меня\"")
    public LoginTests selectCheckbox() {
        $(".toggle-switch").click();
        return this;
    }

    @Step("Нажимаем кнопку \"Вход\"")
    public LoginTests pressEnter() {
        loginButton.click();
        return this;
    }
    @Step("Проверяем результат авторизации")
    public LoginTests verifyResultWhenLoginValid() {
        $("#recently-viewed").shouldHave(text("Мои недавние просмотры"));
        $("#login-form").shouldNot(exist);
        return this;
    }

    @Step("Проверяем наличие сообщения \"password обязательное поле\"")
    public LoginTests verifyMessageAboutEmptyPassword() {
        $("div.text-neg").shouldHave(text("password обязательное поле"));
        return this;
    }

    @Step("Проверяем наличие сообщения \"email обязательное поле.\"")
    public LoginTests verifyMessageEmailRequiredField() {
        $("div.text-neg").shouldHave(text("email обязательное поле."));
        return this;
    }

    @Step("Проверяем работу валидации на корректный ввод email")
    public LoginTests verifyEmailValidationError() {
        $("div.text-neg").shouldHave(text("email должен быть корректным email адресом."));
        return this;
    }

}