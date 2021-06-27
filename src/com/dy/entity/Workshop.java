package com.dy.entity;

/**
 * @author: zjd-DY
 * @date: 2021/6/25 16:12
 * @description:
 */
public class Workshop {
    int id;
    String name;
    String leader;

    @Override
    public String toString() {
        return "Workshop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leader='" + leader + '\'' +
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

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public Workshop() {
    }

    public Workshop(int id, String name, String leader) {
        this.id = id;
        this.name = name;
        this.leader = leader;
    }
}
