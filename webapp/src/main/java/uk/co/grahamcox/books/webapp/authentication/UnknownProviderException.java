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

/**
 * Exception thrown when the authentication provider is unknown
 */
public class UnknownProviderException extends Exception {
    /** The name of the provider */
    private String provider;

    /**
     * Construct the exception
     * @param provider the name of the provider
     */
    public UnknownProviderException(String provider) {
        this.provider = provider;
    }

    /**
     * Get the name of the provider
     * @return the name of the provider
     */
    public String getProvider() {
        return provider;
    }
}
