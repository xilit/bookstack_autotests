package extensions;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AllureSelenideExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)         // прикреплять скриншоты
                .savePageSource(true));    // сохранять HTML страницы
    }
}