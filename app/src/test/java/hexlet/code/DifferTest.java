package hexlet.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DifferTest {
    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;

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
            e.printStackTrace();
            throw new RuntimeException("Error reading fixture file: " + fileName, e);
        }
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        // Тестируем вызов метода с каждым из форматтеров, а также вызов с форматтером по умолчанию
        Assertions.assertThat(Differ.generate(filePath1, filePath2)).isEqualToIgnoringWhitespace(resultStylish);
        Assertions.assertThat(Differ.generate(filePath1, filePath2, "stylish")).isEqualToIgnoringWhitespace(resultStylish);
        Assertions.assertThat(Differ.generate(filePath1, filePath2, "plain")).isEqualToIgnoringWhitespace(resultPlain);
        Assertions.assertThat(Differ.generate(filePath1, filePath2, "json")).isEqualToIgnoringWhitespace(resultJson);
    }
}
