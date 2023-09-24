package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {

    private static String resultStylish;

    private static String resultPlain;
    private static String resultJson;


    public static final String FILE1_JSON_FILEPATH1 =
            "src/test/resources/fixtures/file1.json";
    public static final String FILE2_JSON_FILEPATH2 =
            "src/test/resources/fixtures/file2.json";

    public static final String FILE1_YAML_FILEPATH1 =
            "src/test/resources/fixtures/file1.yml";
    public static final String FILE2_YAML_FILEPATH2 =
            "src/test/resources/fixtures/file2.yml";


    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultStylish = readFixture("expectedStylish");
        resultPlain = readFixture("expectedPlain");
        resultJson = readFixture("jsonExpected.json");
    }

    @Test
    public void testRightComparisonJSON() throws Exception {
        String result = Differ.generate(FILE1_JSON_FILEPATH1, FILE2_JSON_FILEPATH2);
        assertThat(result).isEqualToIgnoringWhitespace(resultStylish);
    }

    @Test
    public void testRightComparisonYAML() throws Exception {
        String result = Differ.generate(FILE1_YAML_FILEPATH1, FILE2_YAML_FILEPATH2);
        assertThat(result).isEqualToIgnoringWhitespace(resultStylish);
    }

    @Test
    public void testRightComparisonStylishJSON() throws Exception {
        String result = Differ.generate(FILE1_JSON_FILEPATH1, FILE2_JSON_FILEPATH2, "stylish");
        assertThat(result).isEqualToIgnoringWhitespace(resultStylish);
    }

    @Test
    public void testRightComparisonStylishYAML() throws Exception {
        String result = Differ.generate(FILE1_YAML_FILEPATH1, FILE2_YAML_FILEPATH2, "stylish");
        assertThat(result).isEqualToIgnoringWhitespace(resultStylish);
    }


    @Test
    public void testRightComparisonPlainJSON() throws Exception {
        String result = Differ.generate(FILE1_JSON_FILEPATH1, FILE2_JSON_FILEPATH2, "plain");
        assertThat(result).isEqualToIgnoringWhitespace(resultPlain);
    }

    @Test
    public void testRightComparisonPlainYAML() throws Exception {
        String result = Differ.generate(FILE1_YAML_FILEPATH1, FILE2_YAML_FILEPATH2, "plain");
        assertThat(result).isEqualToIgnoringWhitespace(resultPlain);
    }

    @Test
    public void testRightComparisonFormatJSONJ() throws Exception {
        String result = Differ.generate(FILE1_JSON_FILEPATH1, FILE2_JSON_FILEPATH2, "json");
        assertThat(result).isEqualTo(resultJson);
    }

    @Test
    public void testRightComparisonFormatJSONY() throws Exception {
        String result = Differ.generate(FILE1_YAML_FILEPATH1, FILE2_YAML_FILEPATH2, "json");
        assertThat(result).isEqualTo(resultJson);
    }
}
