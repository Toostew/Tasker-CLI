package org.Tookaystewie.logic;


public class Tasks {
    private String name;
    private String description;
    private boolean completed;
    private int id;



    public Tasks(String name, String description, boolean completed, int id) {
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.id = id;

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
    public int getId() {return id;}

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
    public void setId(int id) {this.id = id;}

    public void taskView(){
        System.out.println("----------"+getName()+"----------"+"("+getId()+")");
        System.out.println(getDescription());
        System.out.println(getCompleted()  ? "COMPLETED" : "ACTIVE");
    }
    //deprecated, used for debugging
    @Override
    public String toString() {
        return "name: " + name + ", description: " + description + ", status: " + completed;
    }
}
