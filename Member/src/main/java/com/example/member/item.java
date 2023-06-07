package com.example.member;

public class item {
    String name;
    String ID;
    int image;
    String msg;
    String plan;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }



    public item(String name, String ID, int image, String msg, String plan) {
        this.name = name;
        this.ID = ID;
        this.image = image;
        this.msg = msg;
        this.plan = plan;
    }




}
