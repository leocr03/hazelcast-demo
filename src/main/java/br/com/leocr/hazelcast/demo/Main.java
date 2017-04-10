package br.com.leocr.hazelcast.demo;

import br.com.leocr.hazelcast.demo.entities.Queue;
import br.com.leocr.hazelcast.demo.entities.Task;

public class Main {
    public static void main(String [] args) {
        final Queue queue = new Queue(null);
        final HazelcastDemo demo = new HazelcastDemo(queue);

        Task task;
        if ((task = demo.consume()) != null) {
            System.out.printf("Consumed! Name: %s Id: %d%s", task.getName(), task.getId(), System.lineSeparator());
        } else {
            task = new Task.Builder()
                    .name("taskMain")
                    .id(4)
                    .build();
            demo.produce(task);
            System.out.println("Produced! " + task.getName());
        }
    }
}
