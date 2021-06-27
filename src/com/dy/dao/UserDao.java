package com.dy.dao;

import com.dy.entity.User;

/**
 * @author: zjd-DY
 * @date: 2021/6/25 16:19
 * @description:
 */
public interface UserDao {
    //根据账号密码查询（用于登录）
    public User queryByAccPas(String account, String password);

}
