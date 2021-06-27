package com.dy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "CharsetFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "charset", value = "utf-8")
        })
public class CharacterEncodingFilter implements Filter {
    private String filterName;
    private String charset;

    public void destroy() {


        System.out.println(filterName + "字符集过滤");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

		System.out.println(filterName + "doFilter()");
        req.setCharacterEncoding(charset);
        resp.setCharacterEncoding(charset);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

        filterName = config.getFilterName();
        charset = config.getInitParameter("charset");

        System.out.println("初始化：" + filterName);
        System.out.println("编码：" + charset);

    }

}
