package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntQueueTest {

    private IntQueue mQueue;

    @Before
    public void setUp() {
        // Test both implementations
        // mQueue = new LinkedIntQueue(); // Uncomment this for LinkedIntQueue testing
        mQueue = new ArrayIntQueue(); // Testing ArrayIntQueue
    }

    @Test
    public void testIsEmptyInitially() {
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testEnqueueAndNotEmpty() {
        mQueue.enqueue(10);
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testEnqueueDequeue() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        assertEquals(Integer.valueOf(1), mQueue.dequeue());
        assertEquals(Integer.valueOf(2), mQueue.dequeue());
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testPeek() {
        mQueue.enqueue(5);
        assertEquals(Integer.valueOf(5), mQueue.peek());
        assertEquals(Integer.valueOf(5), mQueue.peek()); // Peek should not remove the item
    }

    @Test
    public void testPeekEmptyQueue() {
        assertNull(mQueue.peek());
    }

    @Test
    public void testDequeueEmptyQueue() {
        assertNull(mQueue.dequeue());
    }

    @Test
    public void testSizeChanges() {
        assertEquals(0, mQueue.size());
        mQueue.enqueue(7);
        assertEquals(1, mQueue.size());
        mQueue.enqueue(8);
        assertEquals(2, mQueue.size());
        mQueue.dequeue();
        assertEquals(1, mQueue.size());
        mQueue.dequeue();
        assertEquals(0, mQueue.size());
    }

    @Test
    public void testClearQueue() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.clear();
        assertTrue(mQueue.isEmpty());
        assertEquals(0, mQueue.size());
    }

    @Test
    public void testCircularBufferBehavior() {
        for (int i = 1; i <= 10; i++) {
            mQueue.enqueue(i);
        }
        for (int i = 1; i <= 5; i++) {
            mQueue.dequeue();
        }
        for (int i = 11; i <= 15; i++) {
            mQueue.enqueue(i);
        }
        assertEquals(Integer.valueOf(6), mQueue.dequeue());
        assertEquals(Integer.valueOf(7), mQueue.dequeue());
    }

    @Test
    public void testEnsureCapacityExpands() {
        for (int i = 1; i <= 15; i++) {
            mQueue.enqueue(i);
        }
        assertEquals(15, mQueue.size());
        assertEquals(Integer.valueOf(1), mQueue.dequeue()); // Ensure it still functions after resizing
    }
}
