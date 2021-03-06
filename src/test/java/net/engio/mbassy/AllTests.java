package net.engio.mbassy;

import net.engio.mbassy.bus.ListenerSubscriptionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for running all available unit tests
 *
 * @author bennidi
 *         Date: 11/23/12
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        StrongConcurrentSetTest.class,
        WeakConcurrentSetTest.class,
        AsynchronousMessageBusTest.class,
        SyncBusTest.MBassadorTest.class,
        SyncBusTest.SyncMessageBusTest.class,
        FilterTest.class,
        MetadataReaderTest.class,
        ListenerSubscriptionTest.class,
        MethodDispatchTest.class,
        DeadEventTest.class,
        SynchronizedHandlerTest.class
})
public class AllTests {
}
