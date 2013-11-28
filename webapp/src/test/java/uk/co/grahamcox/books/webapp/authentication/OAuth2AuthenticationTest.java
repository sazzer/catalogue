/*
 * Copyright (C) 28/11/13 graham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.co.grahamcox.books.webapp.authentication;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the OAuth 2.0 Authentication mechanism. This is modelled after the Google system
 */
public class OAuth2AuthenticationTest {
  /** The client ID to use */
  private static final String CLIENT_ID = "clientid.apps.googleusercontent.com";
  /** The base URI to use */
  private static final String URI = "https://accounts.google.com/o/oauth2/auth";
  /** The scopes to use */
  private static final Set<String> SCOPES = new HashSet<String>() {
    {
      add("openid");
      add("email");
    }
  };

  /** The test subject */
  private OAuth2Authentication oAuth2Authentication;

  /**
   * Set up the authentication subject
   * @throws URISyntaxException never
   */
  @Before
  public void setup() throws URISyntaxException {
    oAuth2Authentication = new OAuth2Authentication();
    oAuth2Authentication.setClientId(CLIENT_ID);
    oAuth2Authentication.setAuthUri(new URI(URI));
    oAuth2Authentication.setScopes(SCOPES);
  }

  /**
   * Test the redirect mechanism
   * @throws URISyntaxException never
   */
  @Test
  public void testRedirect() throws URISyntaxException {
    URI redirect = oAuth2Authentication.redirect();
    System.out.print("Redirect URI: " + redirect);

    Assert.assertEquals("https", redirect.getScheme());
    Assert.assertEquals("accounts.google.com", redirect.getHost());
    Assert.assertEquals("/o/oauth2/auth", redirect.getPath());

    Set<String> query = new HashSet<>(Arrays.asList(StringUtils.split(redirect.getQuery(), "&")));
    Assert.assertEquals(4, query.size());
    Assert.assertTrue(query.contains("client_id=" + CLIENT_ID));
    Assert.assertTrue(query.contains("response_type=code"));

    boolean seenState = false;
    for (String q : query) {
      if (q.startsWith("scope=")) {
        String value = q.substring(6);
        Set<String> scopes = new HashSet<>(Arrays.asList(StringUtils.split(value, "+")));
        Assert.assertEquals(SCOPES, scopes);
      }
      else if (q.startsWith("state=")) {
        seenState = true;
      }
    }
    Assert.assertTrue(seenState);
  }
}
