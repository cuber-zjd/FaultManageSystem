package com.dy.Listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author: zjd-DY
 * @date: 2021/6/26 19:03
 * @description:
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    private int numberCount = 0;

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        numberCount--;
        se.getSession().getServletContext().setAttribute("numberCount",numberCount);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        numberCount++;
        se.getSession().getServletContext().setAttribute("numberCount",numberCount);
    }

    public MyHttpSessionListener() {
    }
}
