package com.dy.servlet; /**
 * @author: zjd-DY
 * @date: 2021/6/25 16:08
 * @description:
 */

import com.dy.dao.UserDao;
import com.dy.dao.impl.UserDaoImpl;
import com.dy.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends BaseServlet {
    //用户登录
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account=request.getParameter("username-login");
        String password=request.getParameter("password-login");
        UserDao userDao=new UserDaoImpl();
        User user=userDao.queryByAccPas(account,password);
        if (user.getId()!=0){
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
            response.sendRedirect("index.jsp");
        }else {
            response.sendRedirect("login.jsp?login=fail");
        }
    }
    //退出登录
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        session.setAttribute("user",null);
        response.sendRedirect("login.jsp");
    }
}
