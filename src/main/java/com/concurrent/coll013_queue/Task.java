package com.concurrent.coll013_queue;

public class Task implements Comparable<Task> {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Task task) {
        return Integer.compare(this.id, task.id);
    }

    @Override
    public String toString() {
        return this.id + "," + this.name;
    }

}
