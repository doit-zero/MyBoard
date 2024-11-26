package hello.myboard.config;

import hello.myboard.filter.SessionFilter;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean<SessionFilter> loggingFilter() {
        FilterRegistrationBean<SessionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SessionFilter());
        registrationBean.addUrlPatterns("/write"); // 특정 경로에만 필터 적용
        registrationBean.setOrder(1); // 필터 순서 설정
        return registrationBean;
    }
}
