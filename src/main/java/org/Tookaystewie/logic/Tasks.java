package org.Tookaystewie.logic;


public class Tasks {
    private String name;
    private String description;
    private boolean completed;



    public Tasks(String name, String description, boolean completed) {
        this.name = name;
        this.description = description;
        this.completed = completed;

    }

    //getters-----------------------------
    public String getName() {
        return name;
    }
    public boolean getCompleted() {
        return completed;
    }
    public String getDescription() {
        return description;
    }

    //setters------------------------------
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "name: " + name + ", description: " + description + ", status: " + completed;
    }
}
