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

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.grahamcox.books.webapp.integration.pagemodels.MainPage;

/**
 * Base class for integration tests
 */
public class IntegrationTestBase {
  /** The logger to use */
  private static final Logger LOG = LoggerFactory.getLogger(IntegrationTestBase.class);
  /** The default URL to use */
  public static final String DEFAULT_URL = "http://localhost:8080/webapp";
  /** The main page to use */
  private MainPage mainPage;
  /** The web driver to use */
  private WebDriver webDriver;

  /**
   * Load the page in the web driver
   */
  @Before
  public void loadPage() throws Exception {
    String webdriverClassname = System.getProperty("webdriver");
    String url = System.getProperty("test.url");
    if (url == null || url.isEmpty()) {
      LOG.warn("No URL provided. Using the default of {} and hoping for the best", DEFAULT_URL);
      url = DEFAULT_URL;
    } else {
      LOG.info("Using url {}", url);
    }
    if (webdriverClassname != null && !webdriverClassname.isEmpty()) {
      Class<? extends WebDriver> webDriverClass = (Class<? extends WebDriver>) Class.forName(webdriverClassname);
      webDriver = webDriverClass.newInstance();
    } else {
      webDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
      ((HtmlUnitDriver)webDriver).setJavascriptEnabled(true);
    }
    webDriver.get(url);
    mainPage = new MainPage(webDriver);
  }

  /**
   * Close the page when we're done
   */
  @After
  public void closePage() {
    webDriver.close();
  }

  /**
   * Get the main page
   * @return the main page
   */
  public MainPage getMainPage() {
    return mainPage;
  }

  /**
   * Get the raw web driver
   * @return the raw web driver
   */
  public WebDriver getWebDriver() {
    return webDriver;
  }
}
