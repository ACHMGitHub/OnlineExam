package com.onlineExam.interceptor;

import com.onlineExam.entity.Teacher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TeacherInterceptor implements HandlerInterceptor{

    private final String ADMINSESSION = "currentUser";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object sessionObj = request.getSession().getAttribute(ADMINSESSION);
        if(sessionObj != null && sessionObj instanceof Teacher) {
            return true;
        }
        response.sendRedirect("/studentLogin");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
