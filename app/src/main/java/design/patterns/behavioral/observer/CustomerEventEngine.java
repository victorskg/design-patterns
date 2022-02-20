package design.patterns.behavioral.observer;

import design.patterns.behavioral.observer.listeners.DeleteCustomerListener;
import design.patterns.behavioral.observer.listeners.NotifyCustomerListener;
import design.patterns.behavioral.observer.listeners.SaveCustomerListener;

public class CustomerEventEngine {
    private final CustomerEventManager customerEventManager = new CustomerEventManager(CustomerDomainEvent.values());

    public CustomerEventEngine(SaveCustomerListener saveCustomerListener,
                               DeleteCustomerListener deleteCustomerListener,
                               NotifyCustomerListener notifyCustomerListener) {
        this.customerEventManager.subscribe(CustomerDomainEvent.SAVE_OPERATION, saveCustomerListener);
        this.customerEventManager.subscribe(CustomerDomainEvent.SAVE_OPERATION, notifyCustomerListener);
        this.customerEventManager.subscribe(CustomerDomainEvent.DELETE_OPERATION, deleteCustomerListener);
        this.customerEventManager.subscribe(CustomerDomainEvent.DELETE_OPERATION, notifyCustomerListener);
    }

    public CustomerEventManager getCustomerEventManager() {
        return customerEventManager;
    }
}
