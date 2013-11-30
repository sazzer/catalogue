/*
 * Copyright (C) 30/11/13 graham
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
package uk.co.grahamcox.books.webapp.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * Extension of the Jackson ObjectMapper to add custom functionality
 */
public class ExtendedObjectMapper extends ObjectMapper implements MessageSourceAware {
    /** Serial UID */
    private static final long serialVersionUID = 6801650219656661921L;

    /**
     * Construct, and configure the object mapper
     */
    public ExtendedObjectMapper() {
        super();
        configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    /**
     * Register a module to handle MessageSourceResolvable objects
     * @param messageSource the message source to use
     */
    @Override
    public void setMessageSource(MessageSource messageSource) {
        SimpleModule messageSourceModule = new SimpleModule("messageSource");
        messageSourceModule.addSerializer(new MessageSourceSerializer(messageSource));
        registerModule(messageSourceModule);
    }
}
