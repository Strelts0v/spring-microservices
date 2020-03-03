package com.straltsou.netflixzuulapigatewayserver.logging;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {

        // when we want this filter to be executed:
        // 'pre' - before request execution
        // 'post' - after request execution
        // 'error' - when request caused an exception

        return "pre";
    }

    /**
     * Filter's priority
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * Should filter be executed
     */
    @Override
    public boolean shouldFilter() {
        // you can put sophisticated logic here

        return true; // filter will be executed for every request
    }

    @Override
    public Object run() throws ZuulException {
        val request = RequestContext.getCurrentContext().getRequest();
        logger.info("request -> {} request uri -> {}", request, request.getRequestURI());

        return null;
    }
}
