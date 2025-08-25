package config;

import com.codeborne.selenide.Configuration;

public class TestConfig {
    public static void setup() {
        Configuration.browser = System.getProperty("browser", "chrome"); // Значение по умолчанию
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.pageLoadStrategy = "normal";
        Configuration.timeout = Long.parseLong(System.getProperty("timeout", "5000"));
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.baseUrl = System.getProperty("baseUrl", "http://194.87.222.160");

        // Дополнительные настройки
        Configuration.screenshots = true;
        Configuration.savePageSource = false;
    }
}
