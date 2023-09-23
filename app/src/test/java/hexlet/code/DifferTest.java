package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {
    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String getFixtureContent(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim().replaceAll("\r", "");
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultStylish = getFixtureContent("expectedStylish");
        resultPlain = getFixtureContent("expectedPlain");
        resultJson = getFixtureContent("jsonExpected.json");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        // Json and Yaml to stylish as default
        assertThat(Differ.generate(filePath1, filePath2, "stylish")) // Добавляем формат по умолчанию "stylish"
                .isEqualTo(resultStylish);
        // Json and Yaml to stylish
        assertThat(Differ.generate(filePath1, filePath2, "stylish"))
                .isEqualTo(resultStylish);
        // Json and Yaml to plain
        assertThat(Differ.generate(filePath1, filePath2, "plain"))
                .isEqualTo(resultPlain);
        // Json and Yaml to json
        assertThat(Differ.generate(filePath1, filePath2, "json"))
                .isEqualTo(resultJson);
    }
}
