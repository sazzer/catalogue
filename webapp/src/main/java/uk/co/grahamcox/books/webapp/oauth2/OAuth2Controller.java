/*
 * Copyright (C) 10/11/13 graham
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
package uk.co.grahamcox.books.webapp.oauth2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller responsible for handling the OAuth 2.0 requests
 */
@Controller
@RequestMapping("/oauth/2")
public class OAuth2Controller {
  /**
   * Handle all of the requests that come in on GET /authorize
   */
  @RequestMapping(value = "/authorize", method = RequestMethod.GET)
  @ResponseBody
  public void authorize() {
  }
  /**
   * Handle all of the requests that come in on POST /token
   */
  @RequestMapping(value = "/token", method = RequestMethod.POST)
  @ResponseBody
  public AccessTokenResponse token() {
    AccessTokenResponse accessToken = new AccessTokenResponse();
    accessToken.setAccessToken("abcdef");
    accessToken.setTokenType("bearer");
    accessToken.setExpiresIn(3600L);
    accessToken.setRefreshToken("fedcba");
    accessToken.setScope("a b c");
    return accessToken;
  }
}
