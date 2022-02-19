package design.patterns.behavioral.chainofresponsibility;

import java.util.Optional;

public abstract class ProcessorChain {
    private ProcessorChain next;

    public abstract void process(Order order);

    public ProcessorChain setNext(ProcessorChain nextChain) {
        this.next = nextChain;
        return next;
    }

    protected void processNext(Order order) {
        Optional.ofNullable(next).ifPresent(nextChain -> nextChain.process(order));
    }
}
