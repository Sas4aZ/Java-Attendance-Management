package org.example.model;

public class ModelAttendance {
    private int id ;
    private int classid;
    private int userid;

    public ModelAttendance(int id, int classid, int userid) {
        this.id = id;
        this.classid = classid;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
