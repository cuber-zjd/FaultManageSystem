package com.dy.entity;

import java.util.Calendar;

/**
 * @author: zjd-DY
 * @date: 2021/6/25 16:10
 * @description:
 */
public class User {
    int id;
    String name;
    String status;
    Workshop workshop;
    String account;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", workshop=" + workshop +
                ", account='" + account + '\'' +
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public User(int id, String name, String status, Workshop workshop, String account) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.workshop = workshop;
        this.account = account;
    }

    public User() {
    }
}
