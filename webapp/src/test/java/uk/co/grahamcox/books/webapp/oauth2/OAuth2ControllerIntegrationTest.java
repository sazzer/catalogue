/*
 * Copyright (C) 17/11/13 graham
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
package uk.co.grahamcox.books.webapp.oauth2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Integration test for the OAuth 2 Controller
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/uk/co/grahamcox/books/webapp/test-context.xml")
public class OAuth2ControllerIntegrationTest {
  /** The autowired application context to use */
  @Autowired
  private WebApplicationContext wac;

  /**
   * The Mock MVC Handler to use
   */
  private MockMvc mockMvc;

  /**
   * Set up the mock handler to use
   */
  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }
  /**
   * Test when we call the POST /token endpoint with no parameters at all
   */
  @Test
  public void testTokenNoGrantType() throws Exception {
    mockMvc.perform(post("/oauth/2/token")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.error").exists())
      .andExpect(jsonPath("$.error").value("unsupported_grant_type"));
  }
  /**
   * Test when we call the POST /token endpoint with a grant type that isn't valid
   */
  @Test
  public void testTokenBadGrantType() throws Exception {
    mockMvc.perform(post("/oauth/2/token")
      .param("grant_type", "bad")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.error").exists())
      .andExpect(jsonPath("$.error").value("unsupported_grant_type"));
  }
}
