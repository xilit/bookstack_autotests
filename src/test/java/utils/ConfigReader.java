package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        // Получаем окружение из переменной окружения или system property
        String env = System.getProperty("env", "dev"); // по умолчанию "dev"
        String fileName = String.format("testsdata/users-%s.properties", env);

        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Файл конфигурации не найден: " + fileName);
            }
            properties.load(input);
            System.out.println("✅ Загружен файл конфигурации: " + fileName);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке файла конфигурации: " + fileName, e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
