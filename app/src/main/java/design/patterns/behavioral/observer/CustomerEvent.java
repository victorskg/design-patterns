package design.patterns.behavioral.observer;

public record CustomerEvent(CustomerDomainEvent customerDomainEvent, Customer customer) {
}
