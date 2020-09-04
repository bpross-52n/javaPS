package org.n52.javaps.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.inject.Inject;
import javax.inject.Provider;

import org.n52.javaps.io.InputHandler;
import org.n52.javaps.io.bbox.BoundingBoxInputOutputHandler;
import org.n52.javaps.io.literal.LiteralInputOutputHandler;
import org.n52.javaps.utils.SpringContext;

public class InputProviderList implements Collection<Provider<InputHandler>> {

    private Collection<Provider<InputHandler>> internalProviderList;
        
    public InputProviderList(SpringContext applicationContext) {
        internalProviderList = new ArrayList<Provider<InputHandler>>();

        if (applicationContext != null) {
            Collection<InputHandler> inputHandlerBeans = applicationContext.getInstances(InputHandler.class);

            for (InputHandler inputHandler : inputHandlerBeans) {
                internalProviderList.add(new SimpleProvider<InputHandler>(inputHandler));
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
    public Iterator<Provider<InputHandler>> iterator() {
        
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
    public boolean add(Provider<InputHandler> e) {
        
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
    public boolean addAll(Collection<? extends Provider<InputHandler>> c) {
        
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
