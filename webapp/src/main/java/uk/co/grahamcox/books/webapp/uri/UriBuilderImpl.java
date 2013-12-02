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

/**
 * The actual implementation of a UriBuilder that works in terms of an HTTP Request
 */
public class UriBuilderImpl implements UriBuilder {

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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
