package design.patterns.behavioral;

import design.patterns.behavioral.observer.Customer;
import design.patterns.behavioral.observer.CustomerDomainEvent;
import design.patterns.behavioral.observer.CustomerEvent;
import design.patterns.behavioral.observer.CustomerEventEngine;
import design.patterns.behavioral.observer.listeners.DeleteCustomerListener;
import design.patterns.behavioral.observer.listeners.NotifyCustomerListener;
import design.patterns.behavioral.observer.listeners.SaveCustomerListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ObserverTest {
    private final Customer customer = new Customer("John Doe", "john@doe.com");
    private final SaveCustomerListener saveCustomerListener = spy(SaveCustomerListener.class);
    private final DeleteCustomerListener deleteCustomerListener = spy(DeleteCustomerListener.class);
    private final NotifyCustomerListener notifyCustomerListener = spy(NotifyCustomerListener.class);
    private final CustomerEventEngine customerEventEngine = new CustomerEventEngine(
            saveCustomerListener, deleteCustomerListener, notifyCustomerListener
    );

    @Test
    @DisplayName("Given save operation event should notify all listeners")
    void shouldNotifyAllSaveOperationListeners() {
        var domainEvent = CustomerDomainEvent.SAVE_OPERATION;
        var customerEvent = new CustomerEvent(domainEvent, customer);
        customerEventEngine.getCustomerEventManager().notify(domainEvent, customerEvent);

        verify(saveCustomerListener, times(1)).update(customerEvent);
        verify(notifyCustomerListener, times(1)).update(customerEvent);
    }

    @Test
    @DisplayName("Given delete operation event should notify all listeners")
    void shouldNotifyAllDeleteOperationListeners() {
        var domainEvent = CustomerDomainEvent.DELETE_OPERATION;
        var customerEvent = new CustomerEvent(domainEvent, customer);
        customerEventEngine.getCustomerEventManager().notify(domainEvent, customerEvent);

        verify(deleteCustomerListener, times(1)).update(customerEvent);
        verify(notifyCustomerListener, times(1)).update(customerEvent);
    }

    @Test
    @DisplayName("When unsubscribe save operation should notify only remaining listeners")
    void shouldNotifyOnlyRemainingSaveOperationListeners() {
        var domainEvent = CustomerDomainEvent.SAVE_OPERATION;
        var customerEvent = new CustomerEvent(domainEvent, customer);
        customerEventEngine.getCustomerEventManager().unsubscribe(domainEvent, saveCustomerListener);
        customerEventEngine.getCustomerEventManager().notify(domainEvent, customerEvent);

        verify(saveCustomerListener, times(0)).update(customerEvent);
        verify(notifyCustomerListener, times(1)).update(customerEvent);
    }

    @Test
    @DisplayName("When unsubscribe delete operation should notify only remaining listeners")
    void shouldNotifyOnlyRemainingDeleteOperationListeners() {
        var domainEvent = CustomerDomainEvent.DELETE_OPERATION;
        var customerEvent = new CustomerEvent(domainEvent, customer);
        customerEventEngine.getCustomerEventManager().unsubscribe(domainEvent, deleteCustomerListener);
        customerEventEngine.getCustomerEventManager().notify(domainEvent, customerEvent);

        verify(deleteCustomerListener, times(0)).update(customerEvent);
        verify(notifyCustomerListener, times(1)).update(customerEvent);
    }
}
