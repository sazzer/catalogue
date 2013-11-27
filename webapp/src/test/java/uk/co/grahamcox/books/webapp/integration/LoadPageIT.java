/*
 * Copyright (C) 26/11/13 graham
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
package uk.co.grahamcox.books.webapp.integration;

import org.junit.Assert;
import org.junit.Test;

/**
 * Integration test to test that the page loads correctly
 */
public class LoadPageIT extends IntegrationTestBase {
  /**
   * Test loading the page
   */
  @Test
  public void testLoadPage() {
    // Nothing new here
  }

  /**
   * Test checking if the user is logged in
   */
  @Test
  public void testIsLoggedIn() {
    Assert.assertFalse(getMainPage().getUserArea().isLoggedIn());
  }
}
