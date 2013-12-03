/*
 * Copyright (C) 28/11/13 graham
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
package uk.co.grahamcox.books.webapp.authentication;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;

/**
 * Remote Authentication mechanism that works against a remote OAuth 2.0 provider
 */
public class OAuth2Authentication implements RemoteAuthentication {
    /**
     * The base authentication URI to use
     */
    @NotNull
    @Valid
    @Size(min = 1)
    private URI authUri;
    /**
     * The Client ID to use
     */
    @NotNull
    @Valid
    @Size(min = 1)
    private String clientId;
    /**
     * The Scopes to request
     */
    @NotNull
    @Valid
    private Set<String> scopes = new HashSet<>();

    /**
     * Set the authentication URI to use
     *
     * @param authUri the authentication URI
     */
    public void setAuthUri(URI authUri) {
        this.authUri = authUri;
    }

    /**
     * Set the Client ID to use
     *
     * @param clientId the Client ID
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Set the scopes to request
     *
     * @param scopes the scopes to request
     */
    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    /**
     * Generate a URI to redirect the user to for authentication purposes
     *
     * @param returnTo The URI to return to after authentication has completed   *
     * @return the URI to redirect to
     * @throws URISyntaxException if the URI built is invalid
     */
    @Override
    public URI redirect(URI returnTo) throws URISyntaxException {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("response_type", "code");
        params.put("state", UUID.randomUUID().toString());
        if (returnTo != null) {
            params.put("redirect_uri", returnTo.toString());
        }
        if (!scopes.isEmpty()) {
            String scope = StringUtils.join(scopes, " ");
            params.put("scope", scope);
        }
        StringBuilder uri = new StringBuilder();
        uri.append(authUri).append("?");
        for (Map.Entry<String, String> param : params.entrySet()) {
            try {
                String key = URLEncoder.encode(param.getKey(), "UTF-8");
                String value = URLEncoder.encode(param.getValue(), "UTF-8");
                uri.append(key).append("=").append(value).append("&");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UTF-8 encoding isn't supported!", e);
            }
        }
        return new URI(uri.toString());
    }

    /**
     * Handle the response from a remote authentication
     *
     * @param responseParams the response parameters
     */
    @Override
    public void handleResponse(Map<String, String> responseParams) {
        throw new UnsupportedOperationException();
    }
}
