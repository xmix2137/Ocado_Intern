import com.ocado.basket.BasketSplitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BasketSplitterTest {
    private BasketSplitter basketSplitter;

    @BeforeEach
    void setUp() {
        // Initialize the com.ocado.basket.BasketSplitter object with a sample path to the configuration file
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

    @Test
    void split_MaximumItemsInBasket_ShouldThrowException() {
        // Prepare input data with more than 100 products in the basket
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            items.add("Product" + i);
        }

        // Call the split method and expect an IllegalArgumentException to be thrown
        assertThrows(IllegalArgumentException.class, () -> basketSplitter.split(items));
    }

    @Test
    void constructor_TooManyProductsInConfig_ShouldThrowException() {
        // Prepare a configuration file with more than 1000 products
        String absolutePathToConfigFile = "C:\\Users\\krzyc\\Desktop\\Testowy\\bad_config1.json";

        // Call the constructor and expect an IllegalArgumentException to be thrown
        assertThrows(IllegalArgumentException.class, () -> new BasketSplitter(absolutePathToConfigFile));
    }

    @Test
    void constructor_TooManyDeliveryOptionsInConfig_ShouldThrowException() {
        // Prepare a configuration file with more than 10 delivery options
        String absolutePathToConfigFile = "C:\\Users\\krzyc\\Desktop\\Testowy\\bad_config2.json";

        // Call the constructor and expect an IllegalArgumentException to be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            new BasketSplitter(absolutePathToConfigFile);
        });
    }

}
