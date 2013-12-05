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

import java.io.IOException;
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
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Remote Authentication mechanism that works against a remote OAuth 2.0 provider
 */
public class OAuth2Authentication implements RemoteAuthentication {
    public static final String CHARSET = "UTF-8";
    /** The HTTP Client to use */
    @NotNull
    @Valid
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    /**
     * The base authentication URI to use
     */
    @NotNull
    @Valid
    @Size(min = 1)
    private URI authUri;
    /**
     * The base token URI to use
     */
    @NotNull
    @Valid
    @Size(min = 1)
    private URI tokenUri;
    /**
     * The Client ID to use
     */
    @NotNull
    @Valid
    @Size(min = 1)
    private String clientId;
    /**
     * The Client Secret to use
     */
    @NotNull
    @Valid
    @Size(min = 1)
    private String clientSecret;
    /**
     * The Scopes to request
     */
    @NotNull
    @Valid
    private Set<String> scopes = new HashSet<>();

    /**
     * Set the HTTP Client to use
     * @param httpClient the HTTP Client to use
     */
    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Set the token URI to use
     * @param tokenUri the token URI
     */
    public void setTokenUri(URI tokenUri) {
        this.tokenUri = tokenUri;
    }

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
     * Set the Client Secret to use
     * @param clientSecret the client secret
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
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
        buildQueryString(params, uri);
        return new URI(uri.toString());
    }

    private void buildQueryString(Map<String, String> params, StringBuilder uri) {
        for (Map.Entry<String, String> param : params.entrySet()) {
            try {
                String key = URLEncoder.encode(param.getKey(), "UTF-8");
                String value = URLEncoder.encode(param.getValue(), "UTF-8");
                uri.append(key).append("=").append(value).append("&");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UTF-8 encoding isn't supported!", e);
            }
        }
    }

    /**
     * Handle the response from a remote authentication
     *
     * @return the URI that was redirected to
     * @param responseParams the response parameters
     */
    @Override
    public void handleResponse(URI redirectUri, Map<String, String> responseParams) {
        String code = responseParams.get("code");

        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("redirect_uri", redirectUri.toString());
        params.put("grant_type", "authorization_code");

        StringBuilder body = new StringBuilder();
        buildQueryString(params, body);

        HttpPost httpPost = new HttpPost(tokenUri);
        try {
            httpPost.setEntity(new StringEntity(body.toString(),
                "application/x-www-form-urlencoded",
                CHARSET));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 encoding isn't supported!", e);
        }
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println(EntityUtils.toString(entity));
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
