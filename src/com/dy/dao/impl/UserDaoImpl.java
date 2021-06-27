package com.dy.dao.impl;

import com.dy.dao.UserDao;
import com.dy.entity.User;
import com.dy.utils.DBUtils;
import sun.misc.OSEnvironment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author: zjd-DY
 * @date: 2021/6/25 16:21
 * @description:
 */
public class UserDaoImpl implements UserDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = null;
    int isok;

    //根据账号密码查询（用于登录）
    public User queryByAccPas(String account, String password) {
        try {
            conn = DBUtils.getConnection();
            sql = "SELECT id,name from `user` where account=? and password=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, account);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            User user = new User();
            while (rs.next()) {

                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
            }
            conn.close();
            pstmt.close();
            rs.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
