package design.patterns.behavioral;

import design.patterns.behavioral.strategy.Product;
import design.patterns.behavioral.strategy.ProductShippingEngine;
import design.patterns.behavioral.strategy.ProductShippingType;
import design.patterns.behavioral.strategy.strategies.AzulShippingStrategy;
import design.patterns.behavioral.strategy.strategies.SedexShippingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrategyTest {
    private final Product product = new Product(1, "PlayStation 5");
    private final ProductShippingEngine engine = new ProductShippingEngine(new AzulShippingStrategy(), new SedexShippingStrategy());

    @EnumSource(ProductShippingType.class)
    @ParameterizedTest(name = "{index} - ProductShippingType: {1}")
    @DisplayName("Given the shipping type should resolve to the corresponding strategy")
    void shouldDispatchToTheCorrectStrategyGivenTheShippingType(ProductShippingType shippingType) {
        var result = engine.resolveEngine(shippingType, product);
        assertEquals(shippingType.name(), result);
    }
}
