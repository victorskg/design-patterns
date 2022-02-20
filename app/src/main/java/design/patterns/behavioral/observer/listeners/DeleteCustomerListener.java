package design.patterns.behavioral.observer.listeners;

import design.patterns.behavioral.observer.CustomerEvent;
import design.patterns.behavioral.observer.CustomerEventListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class DeleteCustomerListener implements CustomerEventListener {
    @Override
    public void update(CustomerEvent customerEvent) {
        // Some business logic to delete a customer on a database
        Logger.getLogger("DeleteCustomerListener").log(Level.INFO,
                format("Deleting customer '%s' on the database.", customerEvent.customer().name()));
    }
}
