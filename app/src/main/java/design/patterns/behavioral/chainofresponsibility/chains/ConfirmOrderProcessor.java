package design.patterns.behavioral.chainofresponsibility.chains;

import design.patterns.behavioral.chainofresponsibility.Order;
import design.patterns.behavioral.chainofresponsibility.ProcessorChain;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class ConfirmOrderProcessor extends ProcessorChain {
    @Override
    public void process(Order order) {
        // Some business logic to confirm the product
        Logger.getLogger("ConfirmOrderProcessor").log(Level.INFO,
                format("Confirming order with %d copies of a %s.", order.quantity(), order.productName()));
        this.processNext(order);
    }
}
