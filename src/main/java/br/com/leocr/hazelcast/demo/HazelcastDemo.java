package br.com.leocr.hazelcast.demo;

import br.com.leocr.hazelcast.demo.entities.Queue;
import br.com.leocr.hazelcast.demo.entities.Task;

public class HazelcastDemo {
    private Queue queue;

    public HazelcastDemo(Queue queue) {
        this.queue = queue;
    }

    public boolean produce(Task task) {
        final Queue queue = new Queue();
        return queue.put(task);
    }

    public int queueSize() {
        return queue.size();
    }

    public Task consume() {
        return queue.pop();
    }

    public void reset() {
        queue.reset();
    }
}
