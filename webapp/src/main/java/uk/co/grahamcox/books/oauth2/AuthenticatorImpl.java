/*
 * Copyright (C) 16/11/13 graham
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
package uk.co.grahamcox.books.oauth2;

import java.util.Collection;

/**
 * Implementation of the Authenticator
 */
public class AuthenticatorImpl implements Authenticator {
  /**
   * Authenticate an account by username and password combination
   *
   * @param username the username
   * @param password the password
   * @param scopes the scopes to authenticate the account for
   * @return the access token
   * @throws uk.co.grahamcox.books.oauth2.OAuthException if an error occurred
   */
  @Override
  public AccessToken authenticate(String username, String password, Collection<String> scopes) throws OAuthException {
    throw new OAuthException(OAuthException.ErrorCode.UnsupportedGrantType);
  }
}
