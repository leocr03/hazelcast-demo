package br.com.leocr.hazelcast.demo.entities;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.BlockingQueue;

public class Queue {
    private BlockingQueue<Task> queue;

    public Queue(Configuration configuration) {
        final HazelcastInstance hazelcastInstance;

        if (configuration != null) {
            hazelcastInstance = Hazelcast.newHazelcastInstance(configuration.getConfig());
        } else {
            hazelcastInstance = Hazelcast.newHazelcastInstance();
        }

        queue = hazelcastInstance.getQueue("tasksIn");
    }

    public boolean put(Task task) {
        boolean result;

        try {
            queue.put(task);
            result = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    public int size() {
        return queue.size();
    }

    public Task pop() {
        return queue.poll();
    }

    public void reset() {
        queue.clear();
    }
}
