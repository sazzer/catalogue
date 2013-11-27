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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for Page Models
 */
public abstract class BasePageModel {
  /** The logger to use */
  private static final Logger LOG = LoggerFactory.getLogger(BasePageModel.class);
  /** The base element to use */
  private WebElement base;

  /**
   * Construct the base page model
   * @param base the base element to use
   */
  public BasePageModel(WebElement base) {
    this.base = base;
  }

  /**
   * Get the base element
   * @return the base element
   */
  public WebElement getBase() {
    return base;
  }

  /**
   * Find the element in the base element
   * @param by the mechanism to identify the element
   * @return the element
   */
  protected WebElement find(By by) {
    return find(base, by);
  }

  /**
   * Find the element in the provided base element
   * @param base the base context to search in
   * @param by the mechanism to identify the element
   * @return the element
   */
  protected static WebElement find(SearchContext base, By by) {
    LOG.debug("Finding element {} in {}", by, base);
    int i = 5;
    int timeout = 100;
    while (true) {
      try {
        return base.findElement(by);
      } catch (NoSuchElementException e) {
        if (i > 0) {
          LOG.warn("Element {} not found. Sleeping for {}ms and retrying", by, timeout);
          try {
            Thread.sleep(timeout);
          } catch (InterruptedException e1) {
            throw e;
          }
          --i;
          timeout *= 2;
        } else {
          LOG.error("Element {} never turned up", by);
          throw e;
        }
      }
    }
  }
  /**
   * Get the class names from the given element
   * @param element element
   * @return the class names
   */
  protected Collection<String> getClassNames(WebElement element) {
    String aClass = element.getAttribute("class");
    Set<String> classnames = new HashSet<>();
    if (aClass != null && !aClass.isEmpty()) {
      classnames.addAll(Arrays.asList(aClass.split(" ")));
    }
    return classnames;
  }
}
