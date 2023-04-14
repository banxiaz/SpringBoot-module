package com.bai.jwt;

import com.auth0.jwt.interfaces.Claim;
import com.bai.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * JWT过滤器中进行token的校验和判断，token不合法直接返回，合法则解密数据并把数据放到request中供后续使用。
 * 为了使过滤器生效，需要在启动类添加注解@ServletComponentScan(basePackages = "com.bai.jwt")。
 */
@Slf4j
@WebFilter(filterName = "JwtFilter", urlPatterns = "/secure/*")
public class JwtFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter.super.init(filterConfig);
        log.info("进入JwtFilter init()");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        final String token = request.getHeader("Authorization");
        log.info("从Header里面的authorization字段获取到token[{}]", token);

        //除了OPTIONS方法，其他请求应该验证JWT
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
        } else {
            if (token == null) {
                response.getWriter().write("没有携带token!");
                return;
            }

            Map<String, Claim> userData = JwtUtil.verifyToken(token);
            if (userData == null) {
                response.getWriter().write("token不合法!");
                return;
            }
            //拦截器拿到用户的信息，放到request中
            int id = userData.get("id").asInt();
            String userName = userData.get("userName").asString();
            String password = userData.get("password").asString();

            request.setAttribute("id", id);
            request.setAttribute("userName", userName);
            request.setAttribute("password", password);
            chain.doFilter(req, res);

        }
    }

    @Override
    public void destroy() {
        // Filter.super.destroy();
        log.info("进入JwtFilter destroy()");

    }
}
