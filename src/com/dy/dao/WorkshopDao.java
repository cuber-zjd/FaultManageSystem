package com.dy.dao;

import com.dy.entity.Workshop;

import java.util.List;

/**
 * @author: zjd-DY
 * @date: 2021/6/25 17:50
 * @description:
 */
public interface WorkshopDao {
    //根据id查询对应的车间信息
    public Workshop queryWorkshopByid(int id);

    //查询所有的车间
    public List<Workshop> queryAllWorkshop();

    //查询所有的车间（分页）
    public List<Workshop> queryAllWorkshop(int curPage,int pageSize);

    //查询车间的数据总量
    public int getTotal();

    //更改车间信息
    public boolean updateWorkshop(Workshop workshop);

    //添加车间
    public boolean addWorkshop(Workshop workshop);

    //删除车间
    public boolean deleteWorkshop(int id);
}
