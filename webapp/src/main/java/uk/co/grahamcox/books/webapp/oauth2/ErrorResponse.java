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
package uk.co.grahamcox.books.webapp.oauth2;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Representation of the JSON Response to send for an error
 */
public class ErrorResponse {
  /** The actual error code */
  @NotNull
  @Size(min = 1)
  @JsonProperty("error")
  private String error;
  /** The error description */
  @Size(min = 1)
  @JsonProperty("error_description")
  private String errorDescription;
  /** A URI to help about the error */
  @JsonProperty("error_uri")
  private URI errorUri;
  /** The state parameter from the request, if present */
  @JsonProperty("state")
  private String state;

  /**
   * Get the error code
   * @return the error code
   */
  public String getError() {
    return error;
  }

  /**
   * Set the error code
   * @param error the error code
   */
  public void setError(String error) {
    this.error = error;
  }

  /**
   * Get the error description
   * @return the error description
   */
  public String getErrorDescription() {
    return errorDescription;
  }

  /**
   * Set the error description
   * @param errorDescription the error description
   */
  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }

  /**
   * Get the error URI
   * @return the error URI
   */
  public URI getErrorUri() {
    return errorUri;
  }

  /**
   * Set the error URI
   * @param errorUri the error URI
   */
  public void setErrorUri(URI errorUri) {
    this.errorUri = errorUri;
  }

  /**
   * Get the state parameter
   * @return the state parameter
   */
  public String getState() {
    return state;
  }

  /**
   * Set the state parameter
   * @param state the state parameter
   */
  public void setState(String state) {
    this.state = state;
  }
}
