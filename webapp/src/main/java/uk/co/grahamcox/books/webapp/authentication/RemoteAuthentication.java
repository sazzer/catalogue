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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Mechanism to perform Authentication against a third party provider
 */
public interface RemoteAuthentication {
    /**
     * Generate a URI to redirect the user to for authentication purposes
     *
     * @param returnTo The URI to return to after authentication has completed
     * @return the URI to redirect to
     * @throws URISyntaxException if the URI built is invalid
     */
    public URI redirect(URI returnTo) throws URISyntaxException;

    /**
     * Handle the response from a remote authentication
     * @param responseParams the response parameters
     */
    public void handleResponse(Map<String, String> responseParams);
}
