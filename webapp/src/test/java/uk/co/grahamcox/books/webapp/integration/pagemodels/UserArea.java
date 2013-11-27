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
package uk.co.grahamcox.books.webapp.integration.pagemodels;

import org.openqa.selenium.WebElement;

/**
 * The User Area represents the user display at the top of the page
 */
public class UserArea extends BasePageModel {
  /**
   * Construct the user element
   * @param base the base element to work with
   */
  public UserArea(WebElement base) {
    super(base);
  }

  /**
   * Determine if the user is logged in or not
   * @return true if the user is logged in. False if not
   */
  public boolean isLoggedIn() {
    return getClassNames(getBase()).contains("loggedIn");
  }
}
