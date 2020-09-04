package org.n52.javaps.algorithm;

import javax.inject.Provider;

import org.n52.javaps.io.OutputHandler;

public class SimpleProvider<T> implements Provider<T>{

    private T h;
    
    public SimpleProvider(T h) {
        this.h = h;
    }
    
    @Override
    public T get() {           
        return h;
    }
    
}
