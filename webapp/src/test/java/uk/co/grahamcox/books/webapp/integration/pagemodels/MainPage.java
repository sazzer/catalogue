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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * The page model representing the main page
 */
public class MainPage extends BasePageModel {

  /**
   * Construct the page
   * @param driver the web driver pointing at the page
   */
  public MainPage(WebDriver driver) {
    super(find(driver, By.id("mainPage")));
  }

  /**
   * Get the page model representing the user area
   * @return the user area
   */
  public UserArea getUserArea() {
    return new UserArea(find(By.id("userArea")));
  }
}
