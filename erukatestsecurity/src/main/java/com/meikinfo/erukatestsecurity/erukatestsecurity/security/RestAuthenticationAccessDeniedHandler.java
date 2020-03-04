package com.meikinfo.erukatestsecurity.erukatestsecurity.security;


import com.meikinfo.erukatestsecurity.erukatestsecurity.utils.ResultMassage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *权限不足处理类，返回403
 * @author swh
 * @create: 2020-01-03 13:20
 */
@Component
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().println(ResultMassage.forbidden(e.getMessage()));
        response.getWriter().flush();
    }
}
