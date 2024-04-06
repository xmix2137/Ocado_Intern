import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BasketSplitterTest {
    private BasketSplitter basketSplitter;

    @BeforeEach
    void setUp() {
        // Initialize the BasketSplitter object with a sample path to the configuration file
        basketSplitter = new BasketSplitter("C:\\Users\\krzyc\\Desktop\\Testowy\\config.json");
    }

    @Test
    void split_EmptyItemsList_ShouldReturnEmptyMap() {
        List<String> items = Arrays.asList();
        Map<String, List<String>> result = basketSplitter.split(items);
        assertTrue(result.isEmpty());
    }

    @Test
    void split_ValidInput_ShouldReturnCorrectDeliveryGroups() {
        // Prepare input data
        List<String> items = Arrays.asList(
                "Garlic - Peeled", "Sauce - Mint", "Yogurt - Cherry, 175 Gr",
                "Tea - Apple Green Tea", "Cookies Oatmeal Raisin", "Salt - Rock, Course"
        );

        // Expected results
        Map<String, List<String>> expected = Map.of(
                "Same day delivery", Arrays.asList(
                        "Garlic - Peeled", "Sauce - Mint", "Yogurt - Cherry, 175 Gr", "Tea - Apple Green Tea"
                ),
                "Pick-up point", Arrays.asList("Cookies Oatmeal Raisin"),
                "Courier", Arrays.asList("Salt - Rock, Course")
        );

        // Call the split method
        Map<String, List<String>> result = basketSplitter.split(items);

        // Compare expected results with actual results
        assertEquals(expected, result);
    }
}
