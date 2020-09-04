package org.n52.javaps.algorithm;

import org.n52.iceland.config.spring.ProviderAwareListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;

public class CustomConfigurableContext extends GenericApplicationContext {

    @Override
    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException {
        return new ProviderAwareListableBeanFactory();
    }
}
