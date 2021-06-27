package com.dy.servlet; /**
 * @author: zjd-DY
 * @date: 2021/6/25 17:49
 * @description:
 */

import com.dy.dao.ClazzDao;
import com.dy.dao.FaultDao;
import com.dy.dao.WorkshopDao;
import com.dy.dao.impl.ClazzDaoImpl;
import com.dy.dao.impl.FaultDaoImpl;
import com.dy.dao.impl.WorkshopDaoImpl;
import com.dy.entity.Clazz;
import com.dy.entity.Fault;
import com.dy.entity.User;
import com.dy.entity.Workshop;
import com.dy.utils.PageBeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "WorkshopServlet", value = "/WorkshopServlet")
public class WorkshopServlet extends BaseServlet {
    //查询所有的车间
    protected void queryAllWorkshop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WorkshopDao workshopDao=new WorkshopDaoImpl();

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
            totalData = workshopDao.getTotal();

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
        List<Workshop> workshopList = null;
        workshopList = workshopDao.queryAllWorkshop(currentPage, pageSize);
        //创建page类，存储页码信息及所有的学生信息
        PageBeanUtils pg = new PageBeanUtils(currentPage, pageSize, totalData);
        pg.setPageData(workshopList);
        request.setAttribute("pg", pg);
        request.getRequestDispatcher("/pages/workshop/workshopmanage.jsp").forward(request,response);
    }
    //更新车间信息
    protected void updateWorkshop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id-edit"));
        String name=request.getParameter("name-edit");
        String leader=request.getParameter("leader-edit");
        Workshop workshop=new Workshop();
        workshop.setId(id);
        workshop.setName(name);
        workshop.setLeader(leader);

        WorkshopDao workshopDao=new WorkshopDaoImpl();
        workshopDao.updateWorkshop(workshop);
        request.getRequestDispatcher("WorkshopServlet?action=queryAllWorkshop").forward(request,response);
    }
    //添加车间
    protected void addWorkshop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name-add");
        String leader=request.getParameter("leader-add");
        Workshop workshop=new Workshop();
        workshop.setName(name);
        workshop.setLeader(leader);

        WorkshopDao workshopDao=new WorkshopDaoImpl();
        workshopDao.addWorkshop(workshop);
        request.getRequestDispatcher("WorkshopServlet?action=queryAllWorkshop").forward(request,response);
    }
    //删除车间
    protected void deleteWorkshop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        WorkshopDao workshopDao=new WorkshopDaoImpl();
        workshopDao.deleteWorkshop(id);
        request.getRequestDispatcher("WorkshopServlet?action=queryAllWorkshop").forward(request,response);
    }
}
