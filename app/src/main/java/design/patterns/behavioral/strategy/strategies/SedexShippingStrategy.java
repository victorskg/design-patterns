package design.patterns.behavioral.strategy.strategies;

import design.patterns.behavioral.strategy.Product;
import design.patterns.behavioral.strategy.ProductShippingStrategy;
import design.patterns.behavioral.strategy.ProductShippingType;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SedexShippingStrategy implements ProductShippingStrategy {
    @Override
    public String dispatch(Product product) {
        // Some business logic to dispatch a product via SEDEX
        Logger.getLogger("SedexShippingStrategy").log(Level.INFO, "Dispatching product [{0}] using SEDEX strategy.", product.name());
        return ProductShippingType.SEDEX.name();
    }
}
