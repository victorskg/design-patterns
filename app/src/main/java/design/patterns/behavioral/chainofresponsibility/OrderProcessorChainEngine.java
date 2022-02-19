package design.patterns.behavioral.chainofresponsibility;

import design.patterns.behavioral.chainofresponsibility.chains.ConfirmOrderProcessor;
import design.patterns.behavioral.chainofresponsibility.chains.DispatchProductProcessor;
import design.patterns.behavioral.chainofresponsibility.chains.InventoryValidationProcessor;

public class OrderProcessorChainEngine {
    private final ProcessorChain orderProcessorChain;

    public OrderProcessorChainEngine(InventoryValidationProcessor inventoryValidationProcessor,
                                     ConfirmOrderProcessor confirmOrderProcessor, DispatchProductProcessor dispatchProductProcessor) {
        this.orderProcessorChain = inventoryValidationProcessor;
        this.orderProcessorChain.setNext(confirmOrderProcessor).setNext(dispatchProductProcessor);
    }

    public void process(Order order) {
        orderProcessorChain.process(order);
    }
}
