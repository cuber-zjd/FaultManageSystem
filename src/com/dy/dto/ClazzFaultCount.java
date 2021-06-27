package com.dy.dto;

/**
 * @author: zjd-DY
 * @date: 2021/6/26 18:41
 * @description:
 */
public class ClazzFaultCount {
    private Integer id;
    private String cno;
    private String cname;
    private Integer count;

    @Override
    public String toString() {
        return "ClazzFaultCount{" +
                "id=" + id +
                ", cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", count=" + count +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ClazzFaultCount(Integer id, String cno, String cname, Integer count) {
        this.id = id;
        this.cno = cno;
        this.cname = cname;
        this.count = count;
    }

    public ClazzFaultCount() {
    }
}
