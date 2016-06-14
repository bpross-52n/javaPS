/*
 * Copyright (C) 2013-2015 Christian Autermann
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.n52.javaps.description;

import java.math.BigInteger;
import java.util.Objects;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann
 */
public interface ProcessInputDescriptionBuilder<T extends ProcessInputDescription, B extends ProcessInputDescriptionBuilder<T, B>>
        extends DescriptionBuilder<T, B> {

    B withMaximalOccurence(BigInteger max);

    default B withMaximalOccurence(long max) {
        return withMaximalOccurence(BigInteger.valueOf(max));
    }

    B withMinimalOccurence(BigInteger min);

    default B withMinimalOccurence(long min) {
        return withMinimalOccurence(BigInteger.valueOf(min));
    }

    default B withOccurence(InputOccurence occurence) {
        Objects.requireNonNull(occurence);
        return withMaximalOccurence(occurence.getMax())
                .withMinimalOccurence(occurence.getMin());
    }

}
