/*
 * Copyright 2016 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.n52.iceland.ogc.ows;

import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann
 */
public class OwsAllowedValue extends OwsValueRestriction {

    private final String value;

    public OwsAllowedValue(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            OwsAllowedValue that = (OwsAllowedValue) obj;
            return Objects.equals(this.value, that.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects
                .toStringHelper(this).addValue(this.value).toString();
    }

    @Override
    public OwsAllowedValue asValue() {
        return this;
    }

    @Override
    public boolean isValue() {
        return true;
    }

    public static OwsAllowedValue of(String value) {
        return new OwsAllowedValue(value);
    }
}
