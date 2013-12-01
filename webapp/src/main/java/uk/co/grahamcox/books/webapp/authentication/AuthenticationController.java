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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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
     * Get the list of providers that are configured
     * @return the list of providers
     * @throws URISyntaxException if the URI to the provider is invalid
     */
    @RequestMapping("/remote/providers")
    @ResponseBody
    public Collection<ProviderResponse> listProviders() throws URISyntaxException {
        Set<ProviderResponse> providers = new HashSet<>();
        for (Map.Entry<String, RemoteAuthentication> provider : remoteAuthenticationProviders.entrySet()) {
            if (provider.getValue() != null) {
                String id = provider.getKey();
                ProviderResponse providerResponse = new ProviderResponse();
                providerResponse.setId(id);
                providerResponse.setLabel(new DefaultMessageSourceResolvable("authentication.providers.remote." + id));
                providerResponse.setUri(new URI("http://localhost:8080/webapp/api/auth/remote/" + id));
                providers.add(providerResponse);
            }
        }
        return providers;
    }

    /**
     * Handle the request to redirect the user to the authentication provider
     * @param provider the provider of interest
     * @throws UnknownProviderException if the provider is unknown
     * @throws URISyntaxException if the URI to redirect to is invalid
     */
    @RequestMapping("/remote/{provider}")
    @ResponseBody
    public RedirectResponse redirectToRemote(@PathVariable String provider)
        throws UnknownProviderException, URISyntaxException {
        RemoteAuthentication remoteAuthentication = remoteAuthenticationProviders.get(provider);
        if (remoteAuthentication != null) {
            URI uri = remoteAuthentication.redirect();
            RedirectResponse response = new RedirectResponse();
            response.setUri(uri);
            return response;
        } else {
            throw new UnknownProviderException(provider);
        }
    }

    /**
     * Response representing a providers details
     */
    public static class ProviderResponse {
        /** The ID of the provider */
        private String id;

        /** The label to give to the provider */
        private MessageSourceResolvable label;

        /** The URI to use for authentication */
        private URI uri;
        /**
         * Get the URI to use for authentication
         * @return the URI to use for authentication
         */
        public URI getUri() {
            return uri;
        }

        /**
         * Set the URI to use for authentication
         * @param uri the URI to use for authentication
         */
        public void setUri(URI uri) {
            this.uri = uri;
        }

        /**
         * Get the ID of the provider
         * @return the ID
         */
        public String getId() {
            return id;
        }

        /**
         * Set the ID of the provider
         * @param id the ID
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * Get the label
         * @return the label
         */
        public MessageSourceResolvable getLabel() {
            return label;
        }

        /**
         * Set the label
         * @param label the label
         */
        public void setLabel(MessageSourceResolvable label) {
            this.label = label;
        }
    }

    /**
     * Response to indicate the client needs to redirect the user
     */
    public static class RedirectResponse {
        /** The URI to redirect to */
        private URI uri;
        /** The action to perform */
        private String action = "redirect";
        /**
         * Get the URI to redirect to
         * @return the URI to redirect to
         */
        public URI getUri() {
            return uri;
        }

        /**
         * Set the URI to redirect to
         * @param uri the URI to redirect to
         */
        public void setUri(URI uri) {
            this.uri = uri;
        }

        /**
         * Get the action to perform
         * @return the action
         */
        public String getAction() {
            return action;
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
