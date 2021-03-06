/*
 * Copyright (C) 18/11/13 graham
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
package uk.co.grahamcox.books.user;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

/**
 * Representation of a user
 */
public class User implements Serializable {
  /** The Serial UID */
  private static final long serialVersionUID = -1593974527331984301L;
  /** The User ID of the user */
  @Valid
  private UserId userId;
  /** The password of the user */
  @NotNull
  @Valid
  private Password password;
  /** The email address of the user */
  @NotNull
  @Size(min = 1)
  private String email;
  /** Whether the user is active */
  private boolean active;
  /** When the user was created */
  private DateTime created;
  /** When the user was last modified */
  private DateTime modified;

  /**
   * Get the User ID
   * @return the user ID
   */
  public UserId getUserId() {
    return userId;
  }

  /**
   * Set the User ID
   * @param userId The user ID
   */
  public void setUserId(UserId userId) {
    this.userId = userId;
  }

  /**
   * Get the password
   * @return the password
   */
  public Password getPassword() {
    return password;
  }

  /**
   * Set the password
   * @param password The password
   */
  public void setPassword(Password password) {
    this.password = password;
  }

  /**
   * Get the email address
   * @return the email address
   */
  public String getEmail() {
    return email;
  }

  /**
   * Set the email address
   * @param email The email address
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Check if the user is active
   * @return true if the user is active. False if not
   */
  public boolean isActive() {
    return active;
  }

  /**
   * Set the user active flag
   * @param active True for the user to be active. False for it to be inactive
   */
  public void setActive(boolean active) {
    this.active = active;
  }

  /**
   * Get when the user was created
   * @return when the user was created
   */
  public DateTime getCreated() {
    return created;
  }

  /**
   * Set when the user was created
   * @param created When the user was created
   */
  public void setCreated(DateTime created) {
    this.created = created;
  }

  /**
   * Get when the user was modified
   * @return when the user was modified
   */
  public DateTime getModified() {
    return modified;
  }

  /**
   * Set when the user was modified
   * @param modified When the user was modified
   */
  public void setModified(DateTime modified) {
    this.modified = modified;
  }
  /**
   * Returns a hash code value for the object. This method is
   * supported for the benefit of hash tables such as those provided by
   * {@link java.util.HashMap}.
   * <p/>
   * The general contract of {@code hashCode} is:
   * <ul>
   * <li>Whenever it is invoked on the same object more than once during
   * an execution of a Java application, the {@code hashCode} method
   * must consistently return the same integer, provided no information
   * used in {@code equals} comparisons on the object is modified.
   * This integer need not remain consistent from one execution of an
   * application to another execution of the same application.
   * <li>If two objects are equal according to the {@code equals(Object)}
   * method, then calling the {@code hashCode} method on each of
   * the two objects must produce the same integer result.
   * <li>It is <em>not</em> required that if two objects are unequal
   * according to the {@link Object#equals(Object)}
   * method, then calling the {@code hashCode} method on each of the
   * two objects must produce distinct integer results.  However, the
   * programmer should be aware that producing distinct integer results
   * for unequal objects may improve the performance of hash tables.
   * </ul>
   * <p/>
   * As much as is reasonably practical, the hashCode method defined by
   * class {@code Object} does return distinct integers for distinct
   * objects. (This is typically implemented by converting the internal
   * address of the object into an integer, but this implementation
   * technique is not required by the
   * Java<font size="-2"><sup>TM</sup></font> programming language.)
   *
   * @return a hash code value for this object.
   * @see Object#equals(Object)
   * @see System#identityHashCode
   */
  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * <p/>
   * The {@code equals} method implements an equivalence relation
   * on non-null object references:
   * <ul>
   * <li>It is <i>reflexive</i>: for any non-null reference value
   * {@code x}, {@code x.equals(x)} should return
   * {@code true}.
   * <li>It is <i>symmetric</i>: for any non-null reference values
   * {@code x} and {@code y}, {@code x.equals(y)}
   * should return {@code true} if and only if
   * {@code y.equals(x)} returns {@code true}.
   * <li>It is <i>transitive</i>: for any non-null reference values
   * {@code x}, {@code y}, and {@code z}, if
   * {@code x.equals(y)} returns {@code true} and
   * {@code y.equals(z)} returns {@code true}, then
   * {@code x.equals(z)} should return {@code true}.
   * <li>It is <i>consistent</i>: for any non-null reference values
   * {@code x} and {@code y}, multiple invocations of
   * {@code x.equals(y)} consistently return {@code true}
   * or consistently return {@code false}, provided no
   * information used in {@code equals} comparisons on the
   * objects is modified.
   * <li>For any non-null reference value {@code x},
   * {@code x.equals(null)} should return {@code false}.
   * </ul>
   * <p/>
   * The {@code equals} method for class {@code Object} implements
   * the most discriminating possible equivalence relation on objects;
   * that is, for any non-null reference values {@code x} and
   * {@code y}, this method returns {@code true} if and only
   * if {@code x} and {@code y} refer to the same object
   * ({@code x == y} has the value {@code true}).
   * <p/>
   * Note that it is generally necessary to override the {@code hashCode}
   * method whenever this method is overridden, so as to maintain the
   * general contract for the {@code hashCode} method, which states
   * that equal objects must have equal hash codes.
   *
   * @param obj the reference object with which to compare.
   * @return {@code true} if this object is the same as the obj
   *         argument; {@code false} otherwise.
   * @see #hashCode()
   * @see java.util.HashMap
   */
  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  /**
   * Returns a string representation of the object. In general, the
   * {@code toString} method returns a string that
   * "textually represents" this object. The result should
   * be a concise but informative representation that is easy for a
   * person to read.
   * It is recommended that all subclasses override this method.
   * <p/>
   * The {@code toString} method for class {@code Object}
   * returns a string consisting of the name of the class of which the
   * object is an instance, the at-sign character `{@code @}', and
   * the unsigned hexadecimal representation of the hash code of the
   * object. In other words, this method returns a string equal to the
   * value of:
   * <blockquote>
   * <pre>
   * getClass().getName() + '@' + Integer.toHexString(hashCode())
   * </pre></blockquote>
   *
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}

