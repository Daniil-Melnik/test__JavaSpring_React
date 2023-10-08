package com.test.fullstack;

public class Auto {
    private String name;
    private int id;
    private String comand;
    private String discription;
    private String url;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public String getDiscription() {
        return this.discription;
    }

    public String getComand() {
        return this.comand;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDiscription(String discr) {
        this.discription = discr;
    }

    public void setComand(String comand) {
        this.comand = comand;
    }

    public void setAuto(int id, String name, String comand, String discription, String url){
        setId(id);
        setName(name);
        setComand(comand);
        setUrl(url);
        setDiscription(discription);
    }
}
