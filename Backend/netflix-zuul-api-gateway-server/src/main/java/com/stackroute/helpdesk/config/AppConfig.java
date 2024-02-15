package com.stackroute.helpdesk.config;

import com.stackroute.helpdesk.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    public static final String ACCOUNT_API_URL = "/accountservice/*";
    public static final String PRODUCT_API_URL = "/productservice/*";
    public static final String FAQ_API_URL = "/faqservice/*";
    public static final String QUERY_API_URL = "/queryservice/*";
    public static final String USER_API_URL = "/userservice/*";

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns(ACCOUNT_API_URL, PRODUCT_API_URL, FAQ_API_URL, QUERY_API_URL, USER_API_URL);
        return filterRegistrationBean;
    }

}
