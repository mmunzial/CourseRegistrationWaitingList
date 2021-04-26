package com.example.hw;

public class studentModel {

    private String name;
    private int id;

    private String priority;

    public studentModel(int id, String name, String priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
    }

    //getters and setters

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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    //toString

    @Override
    public String toString() {
        return
                "Id:" + id +
                " Student Name:      " + name+
                "                 Grade: " + priority;
    }
}
