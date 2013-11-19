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
package uk.co.grahamcox.books.user;

import uk.co.grahamcox.books.Id;

/**
 * A User ID
 */
public class UserId extends Id<String> {
  /** The Serial UID */
  private static final long serialVersionUID = -7155644623693025281L;
  /**
   * Construct the ID
   * @param value the value of the ID
   */
  public UserId(String value) {
      super(value);
  }
}

