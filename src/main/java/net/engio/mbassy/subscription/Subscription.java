package net.engio.mbassy.subscription;

import net.engio.mbassy.bus.MessagePublication;
import net.engio.mbassy.common.IConcurrentSet;
import net.engio.mbassy.dispatch.IMessageDispatcher;

import java.util.Comparator;
import java.util.UUID;

/**
 * A subscription is a thread safe container for objects that contain message handlers
 */
public class Subscription {

    private final UUID id = UUID.randomUUID();

    protected final IConcurrentSet<Object> listeners;

    private final IMessageDispatcher dispatcher;

    private final SubscriptionContext context;

    Subscription(SubscriptionContext context, IMessageDispatcher dispatcher, IConcurrentSet<Object> listeners) {
        this.context = context;
        this.dispatcher = dispatcher;
        this.listeners = listeners;
    }


    public boolean handlesMessageType(Class<?> messageType) {
        return context.getHandlerMetadata().handlesMessage(messageType);
    }


    public void publish(MessagePublication publication, Object message) {
        dispatcher.dispatch(publication, message, listeners);
    }

    public int getPriority() {
        return context.getHandlerMetadata().getPriority();
    }


    public void subscribe(Object o) {
        listeners.add(o);
    }


    public boolean unsubscribe(Object existingListener) {
        return listeners.remove(existingListener);
    }

    public int size() {
        return listeners.size();
    }


    public static final Comparator<Subscription> SubscriptionByPriorityDesc = new Comparator<Subscription>() {
        @Override
        public int compare(Subscription o1, Subscription o2) {
            int result = o1.getPriority() - o2.getPriority();
            return result == 0 ? o1.id.compareTo(o2.id) : result;
        }
    };

}
