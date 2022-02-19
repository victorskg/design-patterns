package design.patterns.behavioral;

import design.patterns.behavioral.chainofresponsibility.Order;
import design.patterns.behavioral.chainofresponsibility.OrderProcessorChainEngine;
import design.patterns.behavioral.chainofresponsibility.chains.ConfirmOrderProcessor;
import design.patterns.behavioral.chainofresponsibility.chains.DispatchProductProcessor;
import design.patterns.behavioral.chainofresponsibility.chains.InventoryValidationProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ChainOfResponsibilityTest {
    private final ConfirmOrderProcessor confirmOrderProcessor = spy(new ConfirmOrderProcessor());
    private final DispatchProductProcessor dispatchProductProcessor = spy(new DispatchProductProcessor());
    private final InventoryValidationProcessor inventoryValidationProcessor = spy(new InventoryValidationProcessor());
    private final OrderProcessorChainEngine orderProcessorChainEngine = new OrderProcessorChainEngine(
            inventoryValidationProcessor, confirmOrderProcessor, dispatchProductProcessor
    );

    @Test
    @DisplayName("Given a valid order should run all processors of the chain")
    void shouldRunTheChainGivenValidOrder() {
        var order = new Order("PlayStation 5", 10);
        orderProcessorChainEngine.process(order);

        verify(inventoryValidationProcessor, times(1)).process(order);
        verify(confirmOrderProcessor, times(1)).process(order);
        verify(dispatchProductProcessor, times(1)).process(order);
    }

    @Test
    @DisplayName("Given a invalid order should not run all processors of the chain")
    void shouldNotRunTheChainGivenInvalidOrder() {
        var order = new Order("PlayStation 5", 25);
        var exception = assertThrows(IllegalArgumentException.class,
                () -> orderProcessorChainEngine.process(order));

        verify(inventoryValidationProcessor, times(1)).process(order);
        verify(confirmOrderProcessor, times(0)).process(order);
        verify(dispatchProductProcessor, times(0)).process(order);

        assertEquals(format("Product '%s' not available for given quantity.", order.productName()), exception.getMessage());
    }
}
