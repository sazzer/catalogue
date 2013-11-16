/*
 * Copyright (C) 16/11/13 graham
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
package uk.co.grahamcox.books;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * Base class for an ID
 * @param <T> The type of the ID
 */
public abstract class Id<T> implements Serializable {
    /** The ID value */
    @NotNull
    private final T value;

    /**
     * Construct the ID
     * @param value the value of the ID
     */
    public Id(T value) {
        this.value = value;
    }

    /**
     * Get the value of the ID
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Id id = (Id) o;

        if (!value.equals(id.value)) return false;

        return true;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return value.toString();
    }
}
