/****************************************************************************************************************
 *
 *  Copyright (c) 2013 OCLC, Inc. All Rights Reserved.
 *
 *  OCLC proprietary information: the enclosed materials contain
 *  proprietary information of OCLC, Inc. and shall not be disclosed in whole or in
 *  any part to any third party or used by any person for any purpose, without written
 *  consent of OCLC, Inc.  Duplication of any portion of these materials shall include this notice.
 *
 ******************************************************************************************************************/
package uk.co.grahamcox.books.webapp.uri;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Mechanism by which we can build URIs to parts of the application
 */
public class UriBuilderInterceptor extends HandlerInterceptorAdapter implements UriBuilder {

    /** The ThreadLocal UriBuilder delegates to use */
    private ThreadLocal<UriBuilder> delegates = new ThreadLocal<>();

    /**
     * Handler that is called before the actual controller gets called
     * @param request the request object
     * @param response the response object
     * @param handler the handler object
     * @return true
     * @throws Exception never
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        delegates.set(new UriBuilderImpl(request));
        return true;
    }

    /**
     * Handler that is called after the actual controller gets called
     * @param request the request object
     * @param response the response object
     * @param handler the handler object
     * @param modelAndView the response model and view
     */
    @Override
    public void postHandle(HttpServletRequest request,
        HttpServletResponse response,
        Object handler,
        ModelAndView modelAndView)  {
        delegates.remove();
    }

    /**
     * Get the URI Builder to use for this thread
     * @return the URI Builder
     */
    private UriBuilder getUriBuilder() {
        return delegates.get();
    }
    /**
     * Build a URI to a path with no parameters
     *
     * @param path the path
     * @return the URI
     * @throws URISyntaxException if an error occurs with the URI syntax
     */
    @Override
    public URI buildUri(String path) throws URISyntaxException {
        return buildUri(path, Collections.<String, Object>emptyMap());
    }

    /**
     * Build a URI to a path with parameters
     *
     * @param path the path
     * @param params the parameters
     * @return the URI
     * @throws URISyntaxException if an error occurs with the URI syntax
     */
    @Override
    public URI buildUri(String path, Map<String, Object> params) throws URISyntaxException {
        return getUriBuilder().buildUri(path, params);
    }
}
