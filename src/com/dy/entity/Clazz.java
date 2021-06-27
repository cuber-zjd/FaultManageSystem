package com.dy.entity;

/**
 * @author: zjd-DY
 * @date: 2021/6/25 16:12
 * @description:
 */
public class Clazz {
    int id;
    String name;

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

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

    public Clazz(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Clazz() {
    }
}
