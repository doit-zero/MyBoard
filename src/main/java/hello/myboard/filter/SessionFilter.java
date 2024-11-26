package hello.myboard.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SessionFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(SessionFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        // 요청 경로가 특정 경로일 때만 세션 확인
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/write")) {
            // 쿠키의 JSESSIONID를 읽고 해당 아이디에 맞는 세션이 있는지 없는지 확인함
            HttpSession session = request.getSession(false);
            log.info("session : " + session);
            if (session == null) {
                // null 이거나 해당 아이디로 attribute가 존재하지 않으면
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }

        // 원래 요청대로 진행
        filterChain.doFilter(request, response);
    }

}
