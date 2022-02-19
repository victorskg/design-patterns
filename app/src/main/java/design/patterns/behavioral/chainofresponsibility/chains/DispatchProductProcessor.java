package design.patterns.behavioral.chainofresponsibility.chains;

import design.patterns.behavioral.chainofresponsibility.Order;
import design.patterns.behavioral.chainofresponsibility.ProcessorChain;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class DispatchProductProcessor extends ProcessorChain {
    @Override
    public void process(Order order) {
        // Some business logic to dispatch a product to the customer
        Logger.getLogger("DispatchProductProcessor").log(Level.INFO,
                format("Dispatching %d copies of a %s.", order.quantity(), order.productName()));
        processNext(order);
    }
}
