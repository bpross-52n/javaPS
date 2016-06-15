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
package org.n52.javaps.algorithm;

import org.n52.javaps.description.ProcessDescription;

public abstract class AbstractDescriptorAlgorithm
        extends AbstractObservableAlgorithm {

    private ProcessDescription description;

    @Override
    public synchronized ProcessDescription getDescription() {
        if (description == null) {
            description = createDescription();
        }
        return description;
    }

    @Override
    public boolean processDescriptionIsValid(String version) {
        // TODO process description validation
        return true;
    }

    protected abstract ProcessDescription createDescription();
}
