package com.dy.servlet; /**
 * @author: zjd-DY
 * @date: 2021/6/25 17:47
 * @description:
 */

import com.alibaba.fastjson.JSON;
import com.dy.dao.ClazzDao;
import com.dy.dao.FaultDao;
import com.dy.dao.WorkshopDao;
import com.dy.dao.impl.ClazzDaoImpl;
import com.dy.dao.impl.FaultDaoImpl;
import com.dy.dao.impl.WorkshopDaoImpl;
import com.dy.dto.ClazzFaultCount;
import com.dy.entity.Clazz;
import com.dy.entity.Fault;
import com.dy.entity.Workshop;
import com.dy.utils.PageBeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet(name = "FaultServlet", value = "/FaultServlet")
public class FaultServlet extends BaseServlet {
    //查询某种类型故障
    protected void queryFaultByClazz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clazzid = Integer.parseInt(request.getParameter("clazzid"));
        request.setAttribute("clazzid", clazzid);
        FaultDao faultDao = new FaultDaoImpl();
        if (clazzid == 0) {
            request.getRequestDispatcher("FaultServlet?action=queryAllFault").forward(request, response);
        } else {
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
                totalData = faultDao.getTotalByClazz(clazzid);

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
            //获取学生信息
            List<Fault> faultList = null;
            faultList = faultDao.queryFaultByClazz(clazzid, currentPage, pageSize);
            //获取所有车间
            WorkshopDao workshopDao = new WorkshopDaoImpl();
            List<Workshop> workshopList = workshopDao.queryAllWorkshop();
            //获取所有故障类型
            ClazzDao clazzDao = new ClazzDaoImpl();
            List<Clazz> clazzList = clazzDao.queryAllClazz();
            //创建page类，存储页码信息及所有的学生信息
            PageBeanUtils pg = new PageBeanUtils(currentPage, pageSize, totalData);
            pg.setPageData(faultList);
            request.setAttribute("pg", pg);
            request.setAttribute("workshopList", workshopList);
            request.setAttribute("clazzList", clazzList);
            request.getRequestDispatcher("/pages/fault/faultmanage.jsp").forward(request, response);
        }
    }

    //查询所有故障
    protected void queryAllFault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clazzid = 0;
        request.setAttribute("clazzid", clazzid);
        FaultDao faultDao = new FaultDaoImpl();
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
            totalData = faultDao.getTotal();

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
        //获取所有车间
        WorkshopDao workshopDao = new WorkshopDaoImpl();
        List<Workshop> workshopList = workshopDao.queryAllWorkshop();
        //获取所有故障类型
        ClazzDao clazzDao = new ClazzDaoImpl();
        List<Clazz> clazzList = clazzDao.queryAllClazz();
        //获取车间信息
        List<Fault> faultList = null;
        faultList = faultDao.queryAllFault(currentPage, pageSize);
        //创建page类，存储页码信息及所有的学生信息
        PageBeanUtils pg = new PageBeanUtils(currentPage, pageSize, totalData);
        pg.setPageData(faultList);
        request.setAttribute("pg", pg);
        request.setAttribute("workshopList", workshopList);
        request.setAttribute("clazzList", clazzList);
        request.getRequestDispatcher("/pages/fault/faultmanage.jsp").forward(request, response);
    }

    //修改
    protected void updateFault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id-edit"));
        int workshopid=Integer.parseInt(request.getParameter("workshop-edit"));
        int clazzid=Integer.parseInt(request.getParameter("clazz-edit"));
        String detail=request.getParameter("detail-edit");
        String date=request.getParameter("datetimepicker-edit");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = null;
        try {
            datetime=format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(datetime);
        ClazzDao clazzDao=new ClazzDaoImpl();
        Clazz clazz=clazzDao.queryClazzById(clazzid);
        WorkshopDao workshopDao=new WorkshopDaoImpl();
        Workshop workshop=workshopDao.queryWorkshopByid(workshopid);
        Fault fault=new Fault();
        fault.setId(id);
        fault.setClazz(clazz);
        fault.setTime(calendar);
        fault.setWorkshop(workshop);
        fault.setDetail(detail);

        FaultDao faultDao=new FaultDaoImpl();
        faultDao.updateFaultById(fault);
        request.getRequestDispatcher("FaultServlet?action=queryFaultByClazz&clazzid=0").forward(request,response);
    }

    //添加
    protected void addFault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clazzid=Integer.parseInt(request.getParameter("clazz-add"));
        int workshopid=Integer.parseInt(request.getParameter("workshop-add"));
        String detail=request.getParameter("detail-add");
        String date=request.getParameter("datetimepicker-add");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = null;
        try {
            datetime=format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(datetime);

        Fault fault=new Fault();
        Clazz clazz=new Clazz();
        clazz.setId(clazzid);
        fault.setClazz(clazz);
        Workshop workshop=new Workshop();
        workshop.setId(workshopid);
        fault.setWorkshop(workshop);
        fault.setTime(calendar);
        fault.setDetail(detail);

        FaultDao faultDao=new FaultDaoImpl();
        faultDao.addFault(fault);
        request.getRequestDispatcher("FaultServlet?action=queryFaultByClazz&clazzid=0").forward(request,response);
    }

    //删除
    protected void deleteFaultById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        FaultDao faultDao=new FaultDaoImpl();
        faultDao.deleteFaultById(id);
        request.getRequestDispatcher("FaultServlet?action=queryFaultByClazz&clazzid=0").forward(request,response);
    }

    //统计各个类型故障的个数
    protected void FaultCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FaultDao faultDao=new FaultDaoImpl();
        List<ClazzFaultCount> list = faultDao.FaultCount();

        String result = JSON.toJSONString(list);

        response.setContentType("text/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        out.println(result);
    }
}
