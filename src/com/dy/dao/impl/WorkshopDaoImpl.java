package com.dy.dao.impl;

import com.dy.dao.WorkshopDao;
import com.dy.entity.Workshop;
import com.dy.utils.DBUtils;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zjd-DY
 * @date: 2021/6/25 17:51
 * @description:
 */
public class WorkshopDaoImpl implements WorkshopDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = null;
    int isok;

    //根据id查询对应的车间信息
    public Workshop queryWorkshopByid(int id) {

        try {
            conn = DBUtils.getConnection();
            sql = "SELECT id,name,leader FROM workshop WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            Workshop workshop = new Workshop();
            while (rs.next()) {

                workshop.setId(rs.getInt(1));
                workshop.setName(rs.getString(2));
                workshop.setLeader(rs.getString(3));
            }
            conn.close();
            pstmt.close();
            rs.close();
            return workshop;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询所有的车间(不分页)
    public List<Workshop> queryAllWorkshop() {
        try {
            conn = DBUtils.getConnection();
            sql = "select * from workshop";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Workshop> workshopList = new ArrayList<Workshop>();
            while (rs.next()) {
                Workshop workshop = new Workshop();
                workshop.setId(rs.getInt(1));
                workshop.setName(rs.getString(2));
                workshop.setLeader(rs.getString(3));
                workshopList.add(workshop);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return workshopList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询车间的数据总量
    public int getTotal() {
        try {
            conn = DBUtils.getConnection();
            sql = "select count(id) from workshop";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //查询所有的车间(分页)
    public List<Workshop> queryAllWorkshop(int curPage, int pageSize) {
        try {
            conn = DBUtils.getConnection();
            sql = "select * from workshop limit ?,?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, curPage - 1);
            pstmt.setInt(2, pageSize);
            rs = pstmt.executeQuery();
            List<Workshop> workshopList = new ArrayList<Workshop>();
            while (rs.next()) {
                Workshop workshop = new Workshop();
                workshop.setId(rs.getInt(1));
                workshop.setName(rs.getString(2));
                workshop.setLeader(rs.getString(3));
                workshopList.add(workshop);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return workshopList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //更改车间信息
    public boolean updateWorkshop(Workshop workshop){
        try {
            conn=DBUtils.getConnection();
            sql="update workshop set name=?,leader=? where id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,workshop.getName());
            pstmt.setString(2,workshop.getLeader());
            pstmt.setInt(3,workshop.getId());
            isok=pstmt.executeUpdate();
            if (isok>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //添加车间
    public boolean addWorkshop(Workshop workshop){
        try {
            conn=DBUtils.getConnection();
            sql="insert into workshop(name,leader) values(?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,workshop.getName());
            pstmt.setString(2,workshop.getLeader());
            isok=pstmt.executeUpdate();
            if (isok>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //删除车间
    public boolean deleteWorkshop(int id){
        try {
            conn=DBUtils.getConnection();
            sql="delete from workshop where id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            isok=pstmt.executeUpdate();
            if (isok>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
