package design.patterns.behavioral.chainofresponsibility.chains;

import design.patterns.behavioral.chainofresponsibility.Order;
import design.patterns.behavioral.chainofresponsibility.ProcessorChain;

import java.util.Map;

import static java.lang.String.format;

public class InventoryValidationProcessor extends ProcessorChain {
    private final Map<String, Integer> fakeInventoryDatabase;

    public InventoryValidationProcessor() {
        this.fakeInventoryDatabase = Map.ofEntries(
                Map.entry("PlayStation 5", 20),
                Map.entry("Xbox Series S/X", 5)
        );
    }

    @Override
    public void process(Order order) {
        var product = order.productName();
        var quantity = order.quantity();

        fakeInventoryDatabase.entrySet().stream()
                .filter(entry -> entry.getKey().equals(product) && entry.getValue() > quantity).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("Product '%s' not available for given quantity.", product)));

        processNext(order);
    }
}
