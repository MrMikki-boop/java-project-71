package hexlet.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DifferTest {
    private static final String FIXTURES_FOLDER = "src/test/resources/fixtures/";
    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;
    private static final Logger LOGGER = LoggerFactory.getLogger(DifferTest.class);

    @BeforeAll
    public static void beforeAll() {
        resultStylish = readFixture("expectedStylish");
        resultPlain = readFixture("expectedPlain");
        resultJson = readFixture("jsonExpected.json");
    }

    private static String readFixture(String fileName) {
        try {
            Path filePath = getFixturePath(fileName);
            return Files.readString(filePath).trim();
        } catch (IOException e) {
            LOGGER.error("Error reading fixture file: {}", fileName, e);
            throw new UncheckedIOException("Error reading fixture file: " + fileName, e);
        }
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get(FIXTURES_FOLDER, fileName).toAbsolutePath().normalize();
    }
    /**
     * Генерирует тесты для сравнения файлов в разных форматах.
     *
     * @param format Формат файлов (json или yml).
     * @throws Exception В случае ошибки.
     */
    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        // Тестируем вызов метода с каждым из форматтеров, а также вызов с форматтером по умолчанию
        Assertions.assertThat(Differ.generate(filePath1, filePath2)).isEqualToIgnoringWhitespace(resultStylish);
        Assertions.assertThat(Differ.generate(filePath1, filePath2, "stylish"))
                .isEqualToIgnoringWhitespace(resultStylish);
        Assertions.assertThat(Differ.generate(filePath1, filePath2, "plain"))
                .isEqualToIgnoringWhitespace(resultPlain);
        Assertions.assertThat(Differ.generate(filePath1, filePath2, "json"))
                .isEqualToIgnoringWhitespace(resultJson);
    }
}
