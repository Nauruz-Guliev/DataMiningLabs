package ru.kpfu.itis.gnt.hwpebble.extensions.pebble;

import io.pebbletemplates.pebble.extension.AbstractExtension;
import io.pebbletemplates.pebble.extension.Function;
import io.pebbletemplates.spring.extension.SpringExtension;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.MessageSource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BaseExtension extends AbstractExtension implements BeanFactoryAware {

    private BeanFactory beanFactory;


    @Override
    public Map<String, Function> getFunctions() {
        Map<String, Function> functions = super.getFunctions();
        if(functions == null) {
            functions = new HashMap<>();
        }
        functions.put("lastSeen", beanFactory.getBean(LastSeenFunction.class));
        functions.put("fullUri", beanFactory.getBean(FullUriFunction.class));
        return functions;
    }

    @Override
    public void setBeanFactory(@NonNull BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
