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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The actual implementation of a UriBuilder that works in terms of an HTTP Request
 */
public class UriBuilderImpl implements UriBuilder {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(UriBuilderImpl.class);

    /** The request object to work with */
    private HttpServletRequest request;

    /**
     * Construct the builder
     * @param request the request object
     */
    public UriBuilderImpl(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Build a URI to a path with no parameters
     *
     * @param path the path
     * @return the URI
     * @throws java.net.URISyntaxException if an error occurs with the URI syntax
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
        String originalURI = request.getRequestURL().toString();
        String uriBase = null;

        String pathinfo = request.getPathInfo();
        if (pathinfo.isEmpty()) {
            LOG.debug("Path Info: {}", pathinfo);
            int pathInfoStart = originalURI.indexOf(pathinfo);
            uriBase = originalURI.substring(0, pathInfoStart);
        } else {
            String contextPath = request.getContextPath();
            String servletPath = request.getServletPath();
            String basePath = contextPath + servletPath;
            LOG.debug("Base Path: {}", basePath);

            if (!basePath.isEmpty()) {
                int basePathStart = originalURI.indexOf(basePath);
                int basePathEnd = basePathStart + basePath.length();
                uriBase = originalURI.substring(0, basePathEnd);
            }
        }

        if (uriBase == null) {
            throw new URISyntaxException(originalURI,
                "Original URI didn't have enough details to build the new one from");
        }

        String fullUri = uriBase + path;
        return new URI(fullUri);
    }
}
