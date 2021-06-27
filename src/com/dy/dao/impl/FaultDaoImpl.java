package com.dy.dao.impl;

import com.dy.dao.ClazzDao;
import com.dy.dao.FaultDao;
import com.dy.dao.UserDao;
import com.dy.dao.WorkshopDao;
import com.dy.dto.ClazzFaultCount;
import com.dy.entity.Clazz;
import com.dy.entity.Fault;
import com.dy.entity.User;
import com.dy.entity.Workshop;
import com.dy.utils.DBUtils;
import com.dy.utils.PageBeanUtils;
import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;
import com.sun.xml.internal.ws.runtime.config.TubelineFeatureReader;
import sun.misc.OSEnvironment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author: zjd-DY
 * @date: 2021/6/25 18:00
 * @description:
 */
public class FaultDaoImpl implements FaultDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = null;
    int isok;

    //查询所有的故障信息
    public List<Fault> queryAllFault(int cruPage,int pageSize) {
        try {
            conn = DBUtils.getConnection();
            sql = "select * from fault order by id limit ?,?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,cruPage-1);
            pstmt.setInt(2,pageSize);
            rs = pstmt.executeQuery();
            List<Fault> faultList = new ArrayList<Fault>();
            while (rs.next()) {
                Fault fault = new Fault();
                fault.setId(rs.getInt(1));
                //获取故障时间
                Timestamp timestamp = rs.getTimestamp(2);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timestamp.getTime());
                fault.setTime(calendar);
                //获取故障类型
                ClazzDao clazzDao = new ClazzDaoImpl();
                Clazz clazz = clazzDao.queryClazzById(rs.getInt(3));
                fault.setClazz(clazz);
                //获取车间
                WorkshopDao workshopDao = new WorkshopDaoImpl();
                Workshop workshop = workshopDao.queryWorkshopByid(rs.getInt(4));
                fault.setWorkshop(workshop);
                //获取故障详细信息
                fault.setDetail(rs.getString(5));
                faultList.add(fault);
            }
            return faultList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //查询某种类别故障总数
    public int getTotal() {
        try {
            conn = DBUtils.getConnection();
            sql = "select count(id) from fault";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    //查询某种类别故障
    public List<Fault> queryFaultByClazz(int clazzid) {
        try {
            conn = DBUtils.getConnection();
            sql = "select * from fault where clazzid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, clazzid);
            rs = pstmt.executeQuery();
            List<Fault> faultList = new ArrayList<Fault>();
            while (rs.next()) {
                Fault fault = new Fault();
                fault.setId(rs.getInt(1));
                //获取故障时间
                Timestamp timestamp = rs.getTimestamp(2);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timestamp.getTime());
                fault.setTime(calendar);
                //获取故障类型
                ClazzDao clazzDao = new ClazzDaoImpl();
                Clazz clazz = clazzDao.queryClazzById(rs.getInt(3));
                fault.setClazz(clazz);
                //获取车间
                WorkshopDao workshopDao = new WorkshopDaoImpl();
                Workshop workshop = workshopDao.queryWorkshopByid(rs.getInt(4));
                fault.setWorkshop(workshop);
                //获取故障详细信息
                fault.setDetail(rs.getString(5));
                faultList.add(fault);
            }
            return faultList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //查询某种类别故障（分页）
    public List<Fault> queryFaultByClazz(int clazzid, int curPage, int pageSize) {
        try {
            conn = DBUtils.getConnection();
            sql = "select * from fault where clazzid=? order by id limit ?,?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, clazzid);
            pstmt.setInt(2, curPage);
            pstmt.setInt(3, pageSize);
            rs = pstmt.executeQuery();
            List<Fault> faultList = new ArrayList<Fault>();
            while (rs.next()) {
                Fault fault = new Fault();
                fault.setId(rs.getInt(1));
                //获取故障时间
                Timestamp timestamp = rs.getTimestamp(2);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timestamp.getTime());
                fault.setTime(calendar);
                //获取故障类型
                ClazzDao clazzDao = new ClazzDaoImpl();
                Clazz clazz = clazzDao.queryClazzById(rs.getInt(3));
                fault.setClazz(clazz);
                //获取车间
                WorkshopDao workshopDao = new WorkshopDaoImpl();
                Workshop workshop = workshopDao.queryWorkshopByid(rs.getInt(4));
                fault.setWorkshop(workshop);
                //获取故障详细信息
                fault.setDetail(rs.getString(5));
                faultList.add(fault);
            }
            return faultList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //查询某种类别故障总数
    public int getTotalByClazz(int clazzid) {
        try {
            conn = DBUtils.getConnection();
            sql = "select count(id) from fault where clazzid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, clazzid);
            rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    //根据id修改故障
    public boolean updateFaultById(Fault fault) {
        try {
            conn=DBUtils.getConnection();
            sql="update fault set time=?,clazzid=?,workshopid=?,detail=?  where id=?";
            pstmt=conn.prepareStatement(sql);
            Timestamp timestamp=new Timestamp(fault.getTime().getTimeInMillis());
            pstmt.setTimestamp(1,timestamp);
            pstmt.setInt(2,fault.getClazz().getId());
            pstmt.setInt(3,fault.getWorkshop().getId());
            pstmt.setString(4,fault.getDetail());
            pstmt.setInt(5,fault.getId());
            isok=pstmt.executeUpdate();
            if (isok>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //添加故障
    public boolean addFault(Fault fault){
        try {
            conn=DBUtils.getConnection();
            sql="insert into fault(time,clazzid,workshopid,detail) values(?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            Timestamp timestamp=new Timestamp(fault.getTime().getTimeInMillis());
            pstmt.setTimestamp(1,timestamp);
            pstmt.setInt(2,fault.getClazz().getId());
            pstmt.setInt(3,fault.getWorkshop().getId());
            pstmt.setString(4,fault.getDetail());
            isok= pstmt.executeUpdate();
            if (isok>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //删除故障
    public boolean deleteFaultById(int id){
        try {
            conn=DBUtils.getConnection();
            sql="delete from fault where id=?";
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

    //
    public List<ClazzFaultCount> FaultCount(){
        try {
            conn=DBUtils.getConnection();
            sql="select b.name,count(a.id) from fault a,fault_clazz b where a.clazzid=b.id group by a.clazzid ";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            List<ClazzFaultCount> clazzFaultCountList=new ArrayList<ClazzFaultCount>();
            while (rs.next()){
                ClazzFaultCount clazzFaultCount=new ClazzFaultCount();
                clazzFaultCount.setCname(rs.getString(1));
                clazzFaultCount.setCount(rs.getInt(2));
                clazzFaultCountList.add(clazzFaultCount);
            }
            return clazzFaultCountList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
