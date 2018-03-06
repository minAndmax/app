package com.army.util;

import com.alibaba.druid.pool.DruidDataSource;  
import com.alibaba.druid.support.http.StatViewServlet;  
import com.alibaba.druid.support.http.WebStatFilter;  
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;  
import org.springframework.boot.bind.RelaxedPropertyResolver;  
import org.springframework.boot.web.servlet.FilterRegistrationBean;  
import org.springframework.boot.web.servlet.ServletRegistrationBean;  
import org.springframework.context.EnvironmentAware;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.core.env.Environment;  
import org.springframework.transaction.annotation.EnableTransactionManagement;  
  
import javax.sql.DataSource;  
import java.sql.SQLException;  
import java.util.HashMap;  
import java.util.Map;  

@Configuration
@EnableTransactionManagement  
/**  
 * Druid的DataResource配置类  
 * 凡是被Spring管理的类，实现接口 EnvironmentAware 重写方法 setEnvironment 可以在工程启动时，  
 * 获取到系统环境变量和application配置文件中的变量。 还有一种方式是采用注解的方式获取 @value("${变量的key值}")  
 * 获取application配置文件中的变量。 这里采用第一种要方便些  
 *   
 */  
public class DruidConfiguration implements EnvironmentAware {  
  
    private RelaxedPropertyResolver propertyResolver;  
  
    public void setEnvironment(Environment env) {  
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");  
    }  
  
    @Bean  
    public DataSource dataSource() {  
        DruidDataSource datasource = new DruidDataSource();  
        datasource.setUrl(propertyResolver.getProperty("url"));  
        datasource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));  
        datasource.setUsername(propertyResolver.getProperty("username"));  
        datasource.setPassword(propertyResolver.getProperty("password"));  
        datasource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("initialSize")));  
        datasource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("minIdle")));
        datasource.setValidationQuery(propertyResolver.getProperty("validationQuery"));
        datasource.setMaxWait(Long.valueOf(propertyResolver.getProperty("maxWait")));  
        datasource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("maxActive")));  
        datasource.setMinEvictableIdleTimeMillis(  
                Long.valueOf(propertyResolver.getProperty("minEvictableIdleTimeMillis")));  
        try {  
            datasource.setFilters("stat");  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return datasource;  
    }  
  
    @Bean  
    public ServletRegistrationBean druidServlet() {  
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();  
        servletRegistrationBean.setServlet(new StatViewServlet());  
        servletRegistrationBean.addUrlMappings("/druid/*");  
        Map<String, String> initParameters = new HashMap<String, String>();  
        // initParameters.put("loginUsername", "druid");// 用户名  
        // initParameters.put("loginPassword", "druid");// 密码  
//        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能  
//        initParameters.put("allow", "127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)  
        // initParameters.put("deny", "192.168.20.38");// IP黑名单  
        // (存在共同时，deny优先于allow)  
//        servletRegistrationBean.setInitParameters(initParameters);  
        return servletRegistrationBean;  
    }  
  
    @Bean  
    public FilterRegistrationBean filterRegistrationBean() {  
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
        filterRegistrationBean.setFilter(new WebStatFilter());  
        filterRegistrationBean.addUrlPatterns("/*");  
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");  
        return filterRegistrationBean;  
    }  
  
    @Bean  
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {  
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();  
        beanNameAutoProxyCreator.setProxyTargetClass(true);  
        // 设置要监控的bean的id  
        //beanNameAutoProxyCreator.setBeanNames("sysRoleMapper","loginController");  
        beanNameAutoProxyCreator.setInterceptorNames("druid-stat-interceptor");  
        return beanNameAutoProxyCreator;  
    }  
}