package com.dy.servlet;

import com.dy.dao.ClazzDao;
import com.dy.dao.impl.ClazzDaoImpl;
import com.dy.entity.Clazz;
import com.dy.entity.Workshop;
import com.dy.utils.PageBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: zjd-DY
 * @date: 2021/6/26 17:23
 * @description:
 */
@WebServlet(name="ClazzServlet",value = "/ClazzServlet")
public class ClazzServlet extends BaseServlet{
    //查看所有的故障类型
    protected void queryAllClazz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClazzDao clazzDao=new ClazzDaoImpl();
        //设置当前页数（抛出异常，小于1时，设置当前页数为1）
        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(request.getParameter("curPage"));

            if (currentPage <= 0) {
                currentPage = 1;
            }
        } catch (Exception e) {
            currentPage = 1;

        }
        //设置每页多少数据
        int pageSize = 10;
        //获取一共有多少数据
        int totalData = 0;
        try {
            totalData = clazzDao.getTotal();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //根据总数据量和每页的数据量设置数据总页数
        int totalPage = (int) Math.ceil((double) totalData / pageSize);
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        if (totalData == 0) {
            currentPage = 1;
        }

        //获取车间信息
        List<Clazz> clazzList = null;
        clazzList = clazzDao.queryAllClazz(currentPage, pageSize);
        //创建page类，存储页码信息及所有的学生信息
        PageBeanUtils pg = new PageBeanUtils(currentPage, pageSize, totalData);
        pg.setPageData(clazzList);
        request.setAttribute("pg", pg);
        request.getRequestDispatcher("/pages/clazz/clazzmanage.jsp").forward(request,response);
    }

    //修改故障类型
    protected void updateClazz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id-edit"));
        String name=request.getParameter("name-edit");
        Clazz clazz=new Clazz();
        clazz.setId(id);
        clazz.setName(name);
        ClazzDao clazzDao=new ClazzDaoImpl();
        clazzDao.updateClazz(clazz);
        request.getRequestDispatcher("ClazzServlet?action=queryAllClazz").forward(request,response);
    }

    //添加
    protected void addClazz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name-add");
        Clazz clazz=new Clazz();
        clazz.setName(name);
        ClazzDao clazzDao=new ClazzDaoImpl();
        clazzDao.addClazz(clazz);
        request.getRequestDispatcher("ClazzServlet?action=queryAllClazz").forward(request,response);
    }

    //删除
    protected void deleteClazz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        ClazzDao clazzDao=new ClazzDaoImpl();
        clazzDao.deleteClazz(id);
        request.getRequestDispatcher("ClazzServlet?action=queryAllClazz").forward(request,response);
    }
}
