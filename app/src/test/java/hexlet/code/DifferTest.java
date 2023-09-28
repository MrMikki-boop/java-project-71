package hexlet.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {

    private static final String FILE1_JSON_FILEPATH = "src/test/resources/fixtures/file1.json";
    private static final String FILE2_JSON_FILEPATH = "src/test/resources/fixtures/file2.json";
    private static final String FILE1_YAML_FILEPATH = "src/test/resources/fixtures/file1.yml";
    private static final String FILE2_YAML_FILEPATH = "src/test/resources/fixtures/file2.yml";

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

    @Test
    public void testRightComparisonJSON() throws Exception {
        String result = Differ.generate(FILE1_JSON_FILEPATH, FILE2_JSON_FILEPATH);
        assertThat(result).isEqualToIgnoringWhitespace(resultStylish);
    }

    @Test
    public void testRightComparisonYAML() throws Exception {
        String result = Differ.generate(FILE1_YAML_FILEPATH, FILE2_YAML_FILEPATH);
        assertThat(result).isEqualToIgnoringWhitespace(resultStylish);
    }

    @Test
    public void testRightComparisonStylishJSON() throws Exception {
        String result = Differ.generate(FILE1_JSON_FILEPATH, FILE2_JSON_FILEPATH, "stylish");
        assertThat(result).isEqualToIgnoringWhitespace(resultStylish);
    }

    @Test
    public void testRightComparisonStylishYAML() throws Exception {
        String result = Differ.generate(FILE1_YAML_FILEPATH, FILE2_YAML_FILEPATH, "stylish");
        assertThat(result).isEqualToIgnoringWhitespace(resultStylish);
    }

    @Test
    public void testRightComparisonPlainJSON() throws Exception {
        String result = Differ.generate(FILE1_JSON_FILEPATH, FILE2_JSON_FILEPATH, "plain");
        assertThat(result).isEqualToIgnoringWhitespace(resultPlain);
    }

    @Test
    public void testRightComparisonPlainYAML() throws Exception {
        String result = Differ.generate(FILE1_YAML_FILEPATH, FILE2_YAML_FILEPATH, "plain");
        assertThat(result).isEqualToIgnoringWhitespace(resultPlain);
    }

    @Test
    public void testRightComparisonFormatJSONJ() throws Exception {
        String result = Differ.generate(FILE1_JSON_FILEPATH, FILE2_JSON_FILEPATH, "json");
        assertThat(result).isEqualTo(resultJson);
    }

    @Test
    public void testRightComparisonFormatJSONY() throws Exception {
        String result = Differ.generate(FILE1_YAML_FILEPATH, FILE2_YAML_FILEPATH, "json");
        assertThat(result).isEqualTo(resultJson);
    }
}
