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

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.joda.time.DateTime;

/**
 * Representation of an Access Token from an OAuth 2.0 Authentication Session
 */
public class AccessToken implements Serializable {
  /** Serial version UID */
  private static final long serialVersionUID = -6429678908210145112L;
  /** The actual access token value */
  @NotNull
  @Valid
  private AccessTokenId accessToken;
  /** The refresh token for this access token */
  @Valid
  private RefreshTokenId refreshToken;
  /** The type of the token */
  @NotNull
  @Size(min = 1)
  private String tokenType;
  /** When the token expires */
  @NotNull
  private DateTime expiry;
  /** The scopes the token is valid for */
  @NotNull
  private Set<String> scopes = new HashSet<>();

  /**
   * Get the access token
   * @return the access token
   */
  public AccessTokenId getAccessToken() {
      return accessToken;
  }

  /**
   * Set the access token
   * @param accessToken the access token
   */
  public void setAccessToken(AccessTokenId accessToken) {
      this.accessToken = accessToken;
  }

  /**
   * Get the refresh token
   * @return the refresh token
   */
  public RefreshTokenId getRefreshToken() {
      return refreshToken;
  }

  /**
   * Set the refresh token
   * @param refreshToken the refresh token
   */
  public void setRefreshToken(RefreshTokenId refreshToken) {
      this.refreshToken = refreshToken;
  }

  /**
   * Get the token type
   * @return the token type
   */
  public String getTokenType() {
      return tokenType;
  }

  /**
   * Set the token type
   * @param tokenType the token type
   */
  public void setTokenType(String tokenType) {
      this.tokenType = tokenType;
  }

  /**
   * Get the token expiry
   * @return the token expiry
   */
  public DateTime getExpiry() {
      return expiry;
  }

  /**
   * Set the token expiry
   * @param expiry the token expiry
   */
  public void setExpiry(DateTime expiry) {
      this.expiry = expiry;
  }

  /**
   * Get the scopes of the token
   * @return the scopes of the token
   */
  public Set<String> getScopes() {
      return scopes;
  }

  /**
   * Set the scopes of the token
   * @param scopes the scopes of the token
   */
  public void setScopes(Set<String> scopes) {
      this.scopes = scopes;
  }
}
