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
import java.util.Map;

/**
 * Interface describing a means to build URIs
 */
public interface UriBuilder {
    /**
     * Build a URI to a path with no parameters
     * @param path the path
     * @return the URI
     * @throws URISyntaxException if an error occurs with the URI syntax
     */
    URI buildUri(String path) throws URISyntaxException;
    /**
     * Build a URI to a path with parameters
     * @param path the path
     * @param params the parameters
     * @return the URI
     * @throws URISyntaxException if an error occurs with the URI syntax
     */
    URI buildUri(String path, Map<String, Object> params) throws URISyntaxException;
}
