package br.com.leocr.hazelcast.demo;

import br.com.leocr.hazelcast.demo.entities.Queue;
import br.com.leocr.hazelcast.demo.entities.Task;
import sun.security.krb5.Config;

public class HazelcastDemo {
    private Queue queue;

    public HazelcastDemo(Queue queue) {
        this.queue = queue;
    }

    public boolean produce(Task task) {
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
