package design.patterns.behavioral.observer;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Stream;

public class CustomerEventManager {
    private final Map<CustomerDomainEvent, LinkedHashSet<CustomerEventListener>> listeners;

    public CustomerEventManager(CustomerDomainEvent... customerDomainEvents) {
        this.listeners = new HashMap<>();
        Stream.of(customerDomainEvents).forEachOrdered(customerDomainEvent -> this.listeners.put(customerDomainEvent, new LinkedHashSet<>()));
    }

    public void subscribe(CustomerDomainEvent customerDomainEvent, CustomerEventListener customerEventListener) {
        listeners.get(customerDomainEvent).add(customerEventListener);
    }

    public void unsubscribe(CustomerDomainEvent customerDomainEvent, CustomerEventListener customerEventListener) {
        listeners.get(customerDomainEvent).remove(customerEventListener);
    }

    public void notify(CustomerDomainEvent customerDomainEvent, CustomerEvent customerEvent) {
        listeners.get(customerDomainEvent).forEach(listener -> listener.update(customerEvent));
    }
}
