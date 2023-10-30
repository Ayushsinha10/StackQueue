package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import common.QueueEmptyException;
import common.QueueFullException;

import interfaces.IQueue;

/**
 * Tests double stack queue implementation.
 */
public class TestDoubleStackQueue extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;

    /**
     * Tests that the factory constructs a non-null object.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackQueue() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        assertNotNull(queue, "Failure: IFactory.makeDoubleStackQueue returns null, expected non-null object");
    }

    @Test
    public void enqueuedequeue() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        try {
            queue.enqueue(3);
            assertEquals(queue.dequeue(), 3);
            assertEquals(queue.size(), 0);

        } catch (QueueFullException e) {
            e.printStackTrace();
        } catch (QueueEmptyException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void dequeueEmpty() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        boolean thrown = false;
        try {
            queue.dequeue();

        }

        catch (QueueEmptyException e) {
            thrown = true;

        }
        assertTrue(thrown);
    }

    @Test
    public void enqueueOverflow() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        boolean thrown = false;
        try {
            queue.enqueue(3);
            queue.enqueue(3);
            queue.enqueue(3);
            queue.enqueue(3);
            queue.enqueue(3);
            queue.enqueue(3);

        }

        catch (QueueFullException e) {
            thrown = true;

        }
        assertTrue(thrown);
    }
    @Test
    public void enqueueOverflowODD() {
        IQueue queue = getFactory().makeDoubleStackQueue(11);
        boolean thrown = false;
        try {
            queue.enqueue(3);
            queue.enqueue(3);
            queue.enqueue(3);
            queue.enqueue(3);
            queue.enqueue(3);
            queue.enqueue(3);

        }

        catch (QueueFullException e) {
            thrown = true;

        }
        assertTrue(thrown);
    }

    @Test
    public void enqueueenqueuedequeuedequeue() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);

        try {
            queue.enqueue(3);
            queue.enqueue(7);
            assertEquals(queue.dequeue(), 3);
            assertEquals(queue.dequeue(), 7);

        }

        catch (QueueFullException e) {

        } catch (QueueEmptyException e) {

        }

    }

    @Test
    public void EnqueueDequeuEnqueueDequeue() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);

        try {
            queue.enqueue(3);
            assertEquals(queue.dequeue(), 3);
            queue.enqueue(7);
            assertEquals(queue.dequeue(), 7);

        }

        catch (QueueFullException e) {

        } catch (QueueEmptyException e) {

        }

    }

    @Test
    public void size() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);

        try {
            queue.enqueue(3);
            queue.enqueue(7);
            assertEquals(queue.size(), 2);
            queue.dequeue();
            assertEquals(queue.size(), 1);

        }

        catch (QueueFullException e) {

        } catch (QueueEmptyException e) {

        }

    }

    @Test
    public void size2() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);

        try {
            queue.enqueue(3);
            queue.enqueue(7);
            queue.enqueue(23);
            queue.dequeue();
            queue.enqueue(23);
            assertEquals(queue.size(), 3);

        }

        catch (QueueFullException e) {

        } catch (QueueEmptyException e) {

        }

    }

    @Test
    public void isEmpty() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void isEmptyFalse() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);

        try {
            queue.enqueue(3);
        }

        catch (QueueFullException e) {
        }
        assertFalse(queue.isEmpty());

    }

    @Test
    public void isEmptyFalseDequeue() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);

        try {
            queue.enqueue(3);
            queue.enqueue(4);
            queue.dequeue();
        }

        catch (QueueFullException e) {
        } catch (QueueEmptyException e) {

        }
        assertFalse(queue.isEmpty());

    }

    @Test
    public void clear() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);

        try {
            queue.enqueue(3);
            queue.clear();
        }

        catch (QueueFullException e) {
        }
        assertTrue(queue.isEmpty());

    }

    @Test
    public void clearDequeue() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);

        try {
            queue.enqueue(3);
            queue.enqueue(4);
            queue.dequeue();
            queue.clear();
        }

        catch (QueueFullException e) {
        } catch (QueueEmptyException e) {

        }
        assertTrue(queue.isEmpty());

    }

    @Test
    public void enqueueNull() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        boolean thrown = false;
        try {
            queue.enqueue(null);
        } catch (QueueFullException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            thrown = true;

        }
        assertTrue(thrown);
    }
}
