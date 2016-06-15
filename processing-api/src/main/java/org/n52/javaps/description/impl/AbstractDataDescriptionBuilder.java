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
package org.n52.javaps.description.impl;

import java.util.Objects;

import org.n52.javaps.description.DataDescription;
import org.n52.javaps.description.DataDescriptionBuilder;
import org.n52.javaps.io.data.IData;

/**
 *
 * @author Christian Autermann
 */
public abstract class AbstractDataDescriptionBuilder<T extends DataDescription, B extends DataDescriptionBuilder<T, B>> extends AbstractDescriptionBuilder<T, B> implements DataDescriptionBuilder<T, B> {
    private Class<? extends IData> bindingClass;

    Class<? extends IData> getBindingClass() {
        return this.bindingClass;
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public B withBindingClass(Class<? extends IData> bindingClass) {
        this.bindingClass = Objects.requireNonNull(bindingClass);
        return (B) this;
    }

}
