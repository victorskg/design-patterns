package design.patterns.behavioral.observer.listeners;

import design.patterns.behavioral.observer.CustomerEvent;
import design.patterns.behavioral.observer.CustomerEventListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class NotifyCustomerListener implements CustomerEventListener {
    @Override
    public void update(CustomerEvent customerEvent) {
        // Some business logic to notify a customer
        Logger.getLogger("NotifyCustomerListener").log(Level.INFO,
                format("Notifying customer '%s' about %s.", customerEvent.customer().name(), customerEvent.customerDomainEvent()));
    }
}
