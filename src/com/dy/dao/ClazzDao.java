package com.dy.dao;

import com.dy.entity.Clazz;

import java.util.List;

/**
 * @author: zjd-DY
 * @date: 2021/6/25 17:59
 * @description:
 */
public interface ClazzDao {
    //查询对应id的故障类型
    public Clazz queryClazzById(int id);

    //查询所有的故障类型
    public List<Clazz> queryAllClazz();

    //查询故障类型个数
    public int getTotal();

    //查询所有的故障类型(分页)
    public List<Clazz> queryAllClazz(int curpage, int pageSize);

    //修改
    public boolean updateClazz(Clazz clazz);

    //添加
    public boolean addClazz(Clazz clazz);

    //删除
    public boolean deleteClazz(int id);
}
