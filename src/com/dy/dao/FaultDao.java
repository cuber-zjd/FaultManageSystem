package com.dy.dao;

import com.dy.dto.ClazzFaultCount;
import com.dy.entity.Fault;
import com.dy.utils.DBUtils;

import java.util.List;

/**
 * @author: zjd-DY
 * @date: 2021/6/25 18:00
 * @description:
 */
public interface FaultDao {
    //查询所有的故障信息
    public List<Fault> queryAllFault(int cruPage,int pageSize);
    //查询某种类别故障的总数
    public List<Fault> queryFaultByClazz(int clazzid);
    //查询某种类别故障总数
    public int getTotalByClazz(int clazzid);
    //查询某种类别故障（分页）
    public List<Fault> queryFaultByClazz(int clazzid,int curPage,int pageSize);
    //查询某种类别故障总数
    public int getTotal();
    //根据id修改故障
    public boolean updateFaultById(Fault fault);
    //添加故障
    public boolean addFault(Fault fault);
    //删除故障
    public boolean deleteFaultById(int id);
    //统计故障
    public List<ClazzFaultCount> FaultCount();
}
