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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the Password Hasher
 */
public class PasswordHasherTest {
  /** The password hasher to use */
  private PasswordHasher passwordHasher;

  /**
   * Set up the mocks
   * @throws Exception never
   */
  @Before
  public void setUp() throws Exception {
    passwordHasher = new PasswordHasher();
  }

  /**
   * Test hashing a simple password
   */
  @Test
  public void testSimple() {
    String input = "password";
    Password password = passwordHasher.hash(input);
    Assert.assertEquals("5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", password.getHashedPassword());
  }

  /**
   * Test hashing a long password. The password here will be 2,600 characters long
   */
  @Test
  public void testLong() {
    String input = "";
    for (int i = 0; i < 100; ++i) {
      input += "abcdefghijklmnopqrstuvwxyz";
    }
    Password password = passwordHasher.hash(input);
    Assert.assertEquals("f56a8e2e93b177f8b888979100b99894c618e7b4", password.getHashedPassword());
  }

  /**
   * Test hashing a unicode password. The password here includes the Unicode Snowman character - U+2603 - which is
   * actually three bytes when represented in UTF-8, and a Combining Grave Accent - U+0300 - which combines with another
   * character to make the final printing glyph
   */
  @Test
  public void testUnicode() {
    String input = "☃ Ò";
    Password password = passwordHasher.hash(input);
    Assert.assertEquals("9312a5a196428ccbed308d20a1c110e7a67487c9", password.getHashedPassword());
  }
}
