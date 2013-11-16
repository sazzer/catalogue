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

/**
 * Exception raised by handling an OAuth request
 */
public class OAuthException extends Exception {
  /**
   * The OAuth error code
   */
  public static enum ErrorCode {
    InvalidRequest,
    InvalidClient,
    InvalidGrant,
    UnauthorizedClient,
    UnsupportedGrantType,
    InvalidScope
  }

  /** The error code to use */
  private final ErrorCode errorCode;

  /**
   * Constructs a new exception with {@code null} as its detail message.
   * The cause is not initialized, and may subsequently be initialized by a
   * call to {@link #initCause}.
   * @param errorCode the error code to use
   */
  public OAuthException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * Constructs a new exception with the specified detail message.  The
   * cause is not initialized, and may subsequently be initialized by
   * a call to {@link #initCause}.
   *
   * @param errorCode the error code to use
   * @param message the detail message. The detail message is saved for
   * later retrieval by the {@link #getMessage()} method.
   */
  public OAuthException(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  /**
   * Constructs a new exception with the specified detail message and
   * cause.  <p>Note that the detail message associated with
   * {@code cause} is <i>not</i> automatically incorporated in
   * this exception's detail message.
   *
   * @param errorCode the error code to use
   * @param message the detail message (which is saved for later retrieval
   * by the {@link #getMessage()} method).
   * @param cause the cause (which is saved for later retrieval by the
   * {@link #getCause()} method).  (A <tt>null</tt> value is
   * permitted, and indicates that the cause is nonexistent or
   * unknown.)
   * @since 1.4
   */
  public OAuthException(String message, Throwable cause, ErrorCode errorCode) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  /**
   * Constructs a new exception with the specified cause and a detail
   * message of <tt>(cause==null ? null : cause.toString())</tt> (which
   * typically contains the class and detail message of <tt>cause</tt>).
   * This constructor is useful for exceptions that are little more than
   * wrappers for other throwables (for example, {@link
   * java.security.PrivilegedActionException}).
   *
   * @param errorCode the error code to use
   * @param cause the cause (which is saved for later retrieval by the
   * {@link #getCause()} method).  (A <tt>null</tt> value is
   * permitted, and indicates that the cause is nonexistent or
   * unknown.)
   * @since 1.4
   */
  public OAuthException(Throwable cause, ErrorCode errorCode) {
    super(cause);
    this.errorCode = errorCode;
  }

  /**
   * Get the error code
   * @return the error code
   */
  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
