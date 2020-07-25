package com.learn.jeffrey.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/24 18:52
 **/
@Component
@Slf4j
public class RouteFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    // 过滤的核心逻辑
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String user=request.getParameter("user");
        String uri = request.getRequestURI();
        // 若请求中包含/abc8080路径，且没有user请求参数，则无法通过过滤
/*        if(uri.contains("/test") && StringUtils.isEmpty(user)) {
            log.warn("user用户为空");
            // 指定当前请求未通过zuul过滤，默认值为true
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            return false;
        }*/
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("过滤通过后要执行的方法");
        return null;
    }
}
