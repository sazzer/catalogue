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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

/**
 * Mechanism by which a hashed password can be generated from a plain one
 */
public class PasswordHasher {

  /**
   * Hash the password
   * @param password the password
   * @return the hashed password
   */
  public Password hash(String password) {
    byte[] digest;

    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
       digest = messageDigest.digest(password.getBytes("UTF-8"));
    }
    catch (NoSuchAlgorithmException e) {
      throw new UnsupportedOperationException("The message digest SHA-1 is not supported", e);
    } catch (UnsupportedEncodingException e) {
      throw new UnsupportedOperationException("The character encoding UTF-8 is not supported", e);
    }
    String hashed = Hex.encodeHexString(digest);
    return new Password(hashed);
  }
}