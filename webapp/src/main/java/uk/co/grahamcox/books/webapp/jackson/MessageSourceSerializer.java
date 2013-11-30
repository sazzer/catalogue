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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;

/**
 * Serializer that will serialize a MessageSourceResolvable object
 */
public class MessageSourceSerializer extends JsonSerializer<MessageSourceResolvable> {
    /** The message source to use */
    private MessageSource messageSource;

    /**
     * Construct the serializer
     * @param messageSource the message source to use
     */
    public MessageSourceSerializer(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(MessageSourceResolvable value,
        JsonGenerator jgen,
        SerializerProvider provider) throws IOException, JsonProcessingException {
        String string = messageSource.getMessage(value, Locale.getDefault());
        jgen.writeString(string);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<MessageSourceResolvable> handledType() {
        return MessageSourceResolvable.class;
    }
}
