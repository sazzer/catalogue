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

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Representation of the JSON Response to send for an Access Token
 */
public class AccessTokenResponse {
    /**
     * The actual access token value
     */
    @NotNull
    @Size(min = 1)
    @JsonProperty("access_token")
    private String accessToken;
    /**
     * The type of token
     */
    @NotNull
    @Size(min = 1)
    @JsonProperty("token_type")
    private String tokenType;
    /**
     * How long, in seconds, until the token expires
     */
    @NotNull
    @Min(1)
    @JsonProperty("expires_in")
    private long expiresIn;
    /**
     * The optional refresh token
     */
    @Size(min = 1)
    @JsonProperty("refresh_token")
    private String refreshToken;
    /**
     * The scope names for the token
     */
    @Size(min = 1)
    @JsonProperty("scope")
    private String scope;

    /**
     * Get the access token
     * @return the access token
     */
    public String getAccessToken() {
      return accessToken;
    }
    /**
     * Set the access token
     * @param accessToken the access token
     */
    public void setAccessToken(String accessToken) {
      this.accessToken = accessToken;
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
     * Get the expiry time
     * @return the expiry time
     */
    public long getExpiresIn() {
      return expiresIn;
    }
    /**
     * Set the expiry time
     * @param expiresIn the expiry time
     */
    public void setExpiresIn(long expiresIn) {
      this.expiresIn = expiresIn;
    }

    /**
     * Get the refresh token
     * @return the refresh token
     */
    public String getRefreshToken() {
      return refreshToken;
    }
    /**
     * Set the refresh token
     * @param refreshToken the refresh token
     */
    public void setRefreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
    }

    /**
     * Get the token scope
     * @return the token scope
     */
    public String getScope() {
      return scope;
    }
    /**
     * Set the token scope
     * @param scope The token scope
     */
    public void setScope(String scope) {
      this.scope = scope;
    }
}
