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
import javax.servlet.ServletContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;

/**
 * Unit tests for the main URI Builder
 */
public class UriBuilderImplTest {
    /** The test subject */
    private UriBuilderImpl testSubject;
    /** The Mock HTTP Servlet Request to use */
    private MockHttpServletRequest mockHttpServletRequest;

    /**
     * Set up the objects to test
     */
    @Before
    public void setup() {
        mockHttpServletRequest = new MockHttpServletRequest("GET",
            "/webapp/api/auth/remote/providers");
        mockHttpServletRequest.setRemoteHost("localhost");
        mockHttpServletRequest.setServerPort(8080);
        mockHttpServletRequest.setContextPath("/webapp");
        mockHttpServletRequest.setServletPath("/api");
        mockHttpServletRequest.setPathInfo("/auth/remote/providers");
        testSubject = new UriBuilderImpl(mockHttpServletRequest);
    }

    /**
     * Test writing a URI to a simple path with nothing fancy
     * @throws URISyntaxException never
     */
    @Test
    public void testSimple() throws URISyntaxException {
        URI uri = testSubject.buildUri("/auth/remote/google");
        Assert.assertEquals(new URI("http://localhost:8080/webapp/api/auth/remote/google"), uri);
    }
}
