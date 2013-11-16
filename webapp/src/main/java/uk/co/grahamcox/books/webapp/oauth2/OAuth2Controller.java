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

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.co.grahamcox.books.oauth2.AccessToken;
import uk.co.grahamcox.books.oauth2.AccessTokenId;
import uk.co.grahamcox.books.oauth2.OAuthException;
import uk.co.grahamcox.books.oauth2.RefreshTokenId;

/**
 * Controller responsible for handling the OAuth 2.0 requests as defined in RFC-6749.
 */
@Controller
@RequestMapping("/oauth/2")
public class OAuth2Controller {
  /**
   * Handle all of the requests that come in on GET /authorize
   * @param responseType The response type expected
   * @param req The request object to get extra values from
   * @throws OAuthException if an error occurs
   */
  @RequestMapping(value = "/authorize", method = RequestMethod.GET)
  @ResponseBody
  public void authorize(@RequestParam("response_type") String responseType, HttpServletRequest req)
    throws OAuthException {
    if ("code".equals(responseType)) {
        String clientId = req.getParameter("client_id");
        String redirectUri = req.getParameter("redirect_uri");
        String scope = req.getParameter("scope");
        String state = req.getParameter("state");
        authorizationCodeRequest(clientId, redirectUri, scope, state);
    } else if ("token".equals(responseType)) {
        String clientId = req.getParameter("client_id");
        String redirectUri = req.getParameter("redirect_uri");
        String scope = req.getParameter("scope");
        String state = req.getParameter("state");
        implicitGrantRequest(clientId, redirectUri, scope, state);
    } else {
      throw new OAuthException(OAuthException.ErrorCode.UnsupportedGrantType);
    }

  }
  /**
   * Handle all of the requests that come in on POST /token
   * @param grantType The grant type to handle
   * @param req The request object to get extra values from
   * @return the access token response
   * @throws OAuthException if an error occurs
   */
  @RequestMapping(value = "/token", method = RequestMethod.POST)
  @ResponseBody
  public AccessTokenResponse token(@RequestParam("grant_type") String grantType, HttpServletRequest req)
    throws OAuthException {
    AccessToken accessToken;

    if ("authorization_code".equals(grantType)) {
      String code = req.getParameter("code");
      String redirectUri = req.getParameter("redirect_uri");
      String clientId = req.getParameter("client_id");

      accessToken = authorizationCodeAccessTokenGrant(code, redirectUri, clientId);
    } else if ("password".equals(grantType)) {
      String username = req.getParameter("username");
      String password = req.getParameter("password");
      String scope = req.getParameter("scope");
      accessToken = resourceOwnerPasswordCredentialsGrant(username, password, scope);
    } else if ("client_credentials".equals(grantType)) {
      String scope = req.getParameter("scope");
      accessToken = clientCredentialsGrant(scope);
    } else if ("refresh_token".equals(grantType)) {
      String refreshToken = req.getParameter("refresh_token");
      String scope = req.getParameter("scope");
      accessToken = refresh(refreshToken, scope);
    } else {
      throw new OAuthException(OAuthException.ErrorCode.UnsupportedGrantType);
    }

    return translate(accessToken);
  }

  /**
   * Handler for an OAuth exception to return the correct error response
   * @param e the exception
   * @param request the incoming request
   * @return the error response
   */
  @ExceptionHandler(OAuthException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse handleOAuthException(OAuthException e,
    HttpServletRequest request) {
    ErrorResponse response = new ErrorResponse();
    response.setErrorCode(e.getErrorCode());
    response.setErrorDescription(e.getMessage());
    response.setState(request.getParameter("state"));
    return response;
  }
  /**
   * Translate an access token from the OAuth 2.0 version to the JSON version
   * @param accessToken the access token to translate
   * @return the JSON version of the access token
   */
  private AccessTokenResponse translate(AccessToken accessToken) {
    DateTime now = new DateTime().toDateTimeISO();
    Seconds expirySeconds = Seconds.secondsBetween(now, accessToken.getExpiry());

    AccessTokenResponse response = new AccessTokenResponse();
    response.setAccessToken(accessToken.getAccessToken().getValue());
    response.setExpiresIn(expirySeconds.getSeconds());
    response.setTokenType(accessToken.getTokenType());
    response.setScope(StringUtils.join(accessToken.getScopes(), " "));
    if (accessToken.getRefreshToken() != null) {
      response.setRefreshToken(accessToken.getRefreshToken().getValue());
    }
    return response;
  }
  /**
   * Request a redirect to the Authorization Server for authorization. This is from section 4.1.1 of RFC-6749.
   * @param clientId The client ID if the client isn't otherwise identified
   * @param redirectUri The redirect URI that was included in the authorization request
   * @param scope The scopes
   * @param state Opaque value to pass between requests
   */
  private void authorizationCodeRequest(String clientId, String redirectUri, String scope, String state) {
    throw new UnsupportedOperationException();
  }

  /**
   * Handle a request for an Access Token Request as part of an Authorization Token Grant.
   * This is from section 4.1.3 of RFC-6749.
   * @param code The authorization code received from the authorization server
   * @param redirectUri The redirect URI that was included in the authorization request
   * @param clientId The client ID if the client isn't otherwise identified
   */
  private AccessToken authorizationCodeAccessTokenGrant(String code, String redirectUri, String clientId) {
    throw new UnsupportedOperationException();
  }

  /**
   * Implicitly grant an access token and redirect the user using it. This is from section 4.2.1 of RFC-6749.
   * @param clientId The client ID if the client isn't otherwise identified
   * @param redirectUri The redirect URI that was included in the authorization request
   * @param scope The scopes
   * @param state Opaque value to pass between requests
   */
  private void implicitGrantRequest(String clientId, String redirectUri, String scope, String state) {
    throw new UnsupportedOperationException();
  }

  /**
   * Handle a request for a Resource Owner Password Credentials Grant. This is from section 4.3 of RFC-6749.
   * @param username The username
   * @param password The password
   * @param scope The scopes
   */
  private AccessToken resourceOwnerPasswordCredentialsGrant(String username, String password, String scope) {
    AccessToken accessToken = new AccessToken();
    accessToken.setAccessToken(new AccessTokenId(UUID.randomUUID().toString()));
    accessToken.setRefreshToken(new RefreshTokenId(UUID.randomUUID().toString()));
    accessToken.setExpiry(new DateTime().plusHours(1));
    accessToken.setTokenType("bearer");
    if (scope != null && !scope.isEmpty()) {
      accessToken.setScopes(new HashSet<String>(Arrays.asList(scope.split(" "))));
    }
    return accessToken;
  }

  /**
   * Handle a request for a Client Credentials Grant. This is from section 4.4 of RFC-6749.
   * @param scope The scopes
   */
  private AccessToken clientCredentialsGrant(String scope) {
    throw new UnsupportedOperationException();
  }

  /**
   * Refresh a token that was previously issued. This is from section 6 of RFC-6749.
   * @param refreshToken The refresh token to refresh from
   * @param scope The scopes
   */
  private AccessToken refresh(String refreshToken, String scope) {
    throw new UnsupportedOperationException();
  }
}
