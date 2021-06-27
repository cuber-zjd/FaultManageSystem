package com.dy.dao.impl;

import com.dy.dao.ClazzDao;
import com.dy.entity.Clazz;
import com.dy.entity.Workshop;
import com.dy.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zjd-DY
 * @date: 2021/6/25 18:00
 * @description:
 */
public class ClazzDaoImpl implements ClazzDao {

    Connection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    String sql=null;
    int isok;

    //查询对应id的故障类型
    public Clazz queryClazzById(int id) {
        try {
            conn= DBUtils.getConnection();
            sql="select id,name from fault_clazz where id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            Clazz clazz=new Clazz();
            while (rs.next()){

                clazz.setId(rs.getInt(1));
                clazz.setName(rs.getString(2));
            }
            conn.close();
            pstmt.close();
            rs.close();
            return clazz;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询所有的故障类型
    public List<Clazz> queryAllClazz() {
        try {
            conn=DBUtils.getConnection();
            sql="select * from fault_clazz";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            List<Clazz> clazzList=new ArrayList<Clazz>();
            while (rs.next()){
                Clazz clazz=new Clazz();
                clazz.setId(rs.getInt(1));
                clazz.setName(rs.getString(2));
                clazzList.add(clazz);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return clazzList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询故障类型个数
    public int getTotal() {
        try {
            conn=DBUtils.getConnection();
            sql="select count(id) from fault_clazz";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            int count=0;
            while (rs.next()){
                count=rs.getInt(1);
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

    //查询所有的故障类型(分页)
    public List<Clazz> queryAllClazz(int curpage,int pageSize) {
        try {
            conn=DBUtils.getConnection();
            sql="select * from fault_clazz limit ?,?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,curpage);
            pstmt.setInt(2,pageSize);
            rs=pstmt.executeQuery();
            List<Clazz> clazzList=new ArrayList<Clazz>();
            while (rs.next()){
                Clazz clazz=new Clazz();
                clazz.setId(rs.getInt(1));
                clazz.setName(rs.getString(2));
                clazzList.add(clazz);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return clazzList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //修改
    public boolean updateClazz(Clazz clazz){
        try {
            conn=DBUtils.getConnection();
            sql="update fault_clazz set name=? where id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,clazz.getName());
            pstmt.setInt(2,clazz.getId());
            isok=pstmt.executeUpdate();
            if (isok>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //添加
    public boolean addClazz(Clazz clazz){
        try {
            conn=DBUtils.getConnection();
            sql="insert into fault_clazz(name) values(?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,clazz.getName());
            isok=pstmt.executeUpdate();
            if (isok>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //删除
    public boolean deleteClazz(int id){
        try {
            conn=DBUtils.getConnection();
            sql="delete from fault_clazz where id=?";
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
