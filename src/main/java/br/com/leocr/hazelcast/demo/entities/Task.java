package br.com.leocr.hazelcast.demo.entities;

import java.io.Serializable;

public class Task implements Serializable {

    private int id;
    private String name;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static class Builder {

        private int id;
        private String name;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Task build() {
            return new Task(id, name);
        }
    }
}
