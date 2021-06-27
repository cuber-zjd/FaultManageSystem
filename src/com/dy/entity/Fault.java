package com.dy.entity;

import java.util.Calendar;

/**
 * @author: zjd-DY
 * @date: 2021/6/25 16:13
 * @description:
 */
public class Fault {
    int id;
    Calendar time;
    Clazz clazz;
    Workshop workshop;
    String detail;

    @Override
    public String toString() {
        return "Fault{" +
                "id=" + id +
                ", time=" + time +
                ", clazz=" + clazz +
                ", workshop=" + workshop +
                ", detail='" + detail + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Fault(int id, Calendar time, Clazz clazz, Workshop workshop, String detail) {
        this.id = id;
        this.time = time;
        this.clazz = clazz;
        this.workshop = workshop;
        this.detail = detail;
    }

    public Fault() {
    }
}
