package uk.co.grahamcox.books.webapp.oauth2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller responsible for handling the OAuth 2.0 requests
 */
@Controller
@RequestMapping("/oauth/2")
public class OAuth2Controller {
  /**
   * Handle all of the requests that come in on GET /authorize
   */
  @RequestMapping(value = "/authorize", method = RequestMethod.GET)
  @ResponseBody
  public void authorize() {
  }
  /**
   * Handle all of the requests that come in on POST /token
   */
  @RequestMapping(value = "/token", method = RequestMethod.POST)
  @ResponseBody
  public void token() {
  }
}
