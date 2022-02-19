package design.patterns.behavioral.strategy;

import design.patterns.behavioral.strategy.strategies.AzulShippingStrategy;
import design.patterns.behavioral.strategy.strategies.SedexShippingStrategy;

import java.util.Map;

public class ProductShippingEngine {
    private final Map<ProductShippingType, ProductShippingStrategy> productShippingMapper;

    public ProductShippingEngine(AzulShippingStrategy azulShippingStrategy, SedexShippingStrategy sedexShippingStrategy) {
        this.productShippingMapper = Map.ofEntries(
                Map.entry(ProductShippingType.AZUL, azulShippingStrategy),
                Map.entry(ProductShippingType.SEDEX, sedexShippingStrategy)
        );
    }

    public String resolveEngine(ProductShippingType productShippingType, Product product) {
        return this.productShippingMapper.get(productShippingType).dispatch(product);
    }
}
