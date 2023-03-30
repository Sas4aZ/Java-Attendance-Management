package org.example.model;

public class ModelClass {
    private int id;
    private String classname;

    public ModelClass(int id, String classname) {
        this.id = id;
        this.classname = classname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
