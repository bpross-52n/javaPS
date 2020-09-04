/*
 * Copyright 2016-2020 52°North Initiative for Geospatial Open Source
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.inject.Provider;

import org.n52.javaps.io.OutputHandler;
import org.n52.javaps.utils.SpringContext;

public class OutputProviderList implements Collection<Provider<OutputHandler>> {

    private Collection<Provider<OutputHandler>> internalProviderList;

    public OutputProviderList(SpringContext applicationContext) {
        internalProviderList = new ArrayList<Provider<OutputHandler>>();

        if (applicationContext != null) {
            Collection<OutputHandler> outputHandlerBeans = applicationContext.getInstances(OutputHandler.class);

            for (OutputHandler outputHandler : outputHandlerBeans) {
                internalProviderList.add(new SimpleProvider<OutputHandler>(outputHandler));
            }

        } else {
            System.out.println("ApplicationContext is null");
        }
    }

    @Override
    public int size() {
        return internalProviderList.size();
    }

    @Override
    public boolean isEmpty() {
        return internalProviderList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {

        return internalProviderList.contains(o);
    }

    @Override
    public Iterator<Provider<OutputHandler>> iterator() {

        return internalProviderList.iterator();
    }

    @Override
    public Object[] toArray() {

        return internalProviderList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {

        return internalProviderList.toArray(a);
    }

    @Override
    public boolean add(Provider<OutputHandler> e) {

        return internalProviderList.add(e);
    }

    @Override
    public boolean remove(Object o) {

        return internalProviderList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        return internalProviderList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Provider<OutputHandler>> c) {

        return internalProviderList.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        return internalProviderList.remove(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        return internalProviderList.retainAll(c);
    }

    @Override
    public void clear() {
        internalProviderList.clear();
    }

}
