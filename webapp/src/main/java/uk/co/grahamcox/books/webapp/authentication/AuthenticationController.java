/****************************************************************************************************************
 *
 *  Copyright (c) 2013 OCLC, Inc. All Rights Reserved.
 *
 *  OCLC proprietary information: the enclosed materials contain
 *  proprietary information of OCLC, Inc. and shall not be disclosed in whole or in
 *  any part to any third party or used by any person for any purpose, without written
 *  consent of OCLC, Inc.  Duplication of any portion of these materials shall include this notice.
 *
 ******************************************************************************************************************/
package uk.co.grahamcox.books.webapp.authentication;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller to handle authentication
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    /** The map of remote authentication providers */
    @NotNull
    @Valid
    private Map<String, RemoteAuthentication> remoteAuthenticationProviders = new HashMap<>();

    /**
     * Set the map of providers to use
     * @param remoteAuthenticationProviders the map of providers
     */
    public void setRemoteAuthenticationProviders(Map<String, RemoteAuthentication> remoteAuthenticationProviders) {
        this.remoteAuthenticationProviders = remoteAuthenticationProviders;
    }

    /**
     * Handle when the provider requested was unknown
     * @param e the error to handle
     */
    @ExceptionHandler(UnknownProviderException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public UnknownProviderResponse handleUnknownProvider(UnknownProviderException e) {
        UnknownProviderResponse response = new UnknownProviderResponse();
        response.setProvider(e.getProvider());
        return response;
    }

    /**
     * Handle the request to redirect the user to the authentication provider
     * @param provider the provider of interest
     * @throws UnknownProviderException if the provider is unknown
     */
    @RequestMapping("/remote/{provider}")
    @ResponseBody
    public void redirectToRemote(@PathVariable String provider) throws UnknownProviderException {
        if (remoteAuthenticationProviders.containsKey(provider)) {

        } else {
            throw new UnknownProviderException(provider);
        }
    }

    /**
     * Response to indicate that the provider was unknown
     */
    public static class UnknownProviderResponse {
        /** The name of the provider that was unknown */
        private String provider;

        /**
         * Get the provider name
         * @return the provider name
         */
        public String getProvider() {
            return provider;
        }

        /**
         * Set the provider name
         * @param provider the provider name
         */
        public void setProvider(String provider) {
            this.provider = provider;
        }
    }
}
