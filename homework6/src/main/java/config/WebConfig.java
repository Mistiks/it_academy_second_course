package config;

import controller.web.filters.SecurityFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Defining configuration for web components
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
@EnableWebMvc
@Configuration
@ComponentScan("controller")
public class WebConfig implements WebMvcConfigurer {

    /**
     * Adds the provided HandlerInterceptor.
     *
     * @param registry  Helps with configuring a list of mapped interceptors
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new SecurityFilter()).addPathPatterns("/chats", "/message", "/profile");
    }
}