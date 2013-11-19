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

/**
 * Interface describing the means to load Users from the data store
 */
public interface UserDao {
  /**
   * Get the user with the given ID
   * @param userId The ID of the user to look up
   * @return the user with this ID, or null if there wasn't one
   */
  public User getById(UserId userId);
  /**
   * Get the user with the given email address
   * @param email The email address of the user to look up
   * @return the user with this email address, or null if there wasn't one
   */
  public User findUserByEmail(String email);
  /**
   * Save the given User to the data store. This will either create or update the user as appropriate
   * @param user The user to save
   * @return the saved user
   */
  public User saveUser(User user);
}
