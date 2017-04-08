package br.com.leocr.hazelcast.demo;

import br.com.leocr.hazelcast.demo.entities.Queue;
import br.com.leocr.hazelcast.demo.entities.Task;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class HazelcastDemoIT {

    private static HazelcastDemo demo;

    @BeforeClass
    public static void beforeClass() {
        final Queue queue = new Queue();
        demo = new HazelcastDemo(queue);
    }

    @Before
    public void setUp() throws Exception {
        demo.reset();
    }

    @Test
    public void produceSuccess() throws Exception {
        final Task task = new Task.Builder()
                .id(5)
                .name("task1")
                .build();

        int numElements = demo.queueSize();
        assertEquals(0, numElements);

        final boolean result = demo.produce(task);
        assertTrue(result);

        numElements = demo.queueSize();
        assertEquals(1, numElements);
    }

    @Test
    public void consumeSuccess() throws Exception {

        int numElements = demo.queueSize();
        assertEquals(0, numElements);

        createProduce();

        numElements = demo.queueSize();
        assertEquals(1, numElements);

        final Task task = demo.consume();
        assertNotNull(task);
        assertNotNull(task.getName());
        assertEquals("task1", task.getName());

        numElements = demo.queueSize();
        assertEquals(0, numElements);
    }

    private void createProduce() {
        final Task task = new Task.Builder()
                .id(5)
                .name("task1")
                .build();

        demo.produce(task);
    }
}
