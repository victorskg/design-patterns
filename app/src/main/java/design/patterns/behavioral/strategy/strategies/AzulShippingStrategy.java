package design.patterns.behavioral.strategy.strategies;

import design.patterns.behavioral.strategy.Product;
import design.patterns.behavioral.strategy.ProductShippingStrategy;
import design.patterns.behavioral.strategy.ProductShippingType;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AzulShippingStrategy implements ProductShippingStrategy {
    @Override
    public String dispatch(Product product) {
        // Some business logic to dispatch a product via AZUL
        Logger.getLogger("AzulShippingStrategy").log(Level.INFO, "Dispatching product [{0}] using AZUL strategy.", product.name());
        return ProductShippingType.AZUL.name();
    }
}
