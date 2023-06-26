package ru.kpfu.itis.gnt.hwpebble.config;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.extension.AbstractExtension;
import io.pebbletemplates.spring.extension.SpringExtension;
import io.pebbletemplates.spring.servlet.PebbleViewResolver;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.kpfu.itis.gnt.hwpebble.extensions.pebble.BaseExtension;
import ru.kpfu.itis.gnt.hwpebble.extensions.pebble.FullUriFunction;


@Configuration
@Profile("peb")
public class PebbleResolverConfig {

    @Autowired
    private MessageSource messageSource;

    @Bean
    public PebbleViewResolver viewResolver() {
        PebbleViewResolver viewResolver = new PebbleViewResolver(pebbleEngine());
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".peb");
        return viewResolver;
    }

    @Bean
    public PebbleEngine pebbleEngine(){
        return new PebbleEngine.Builder()
                .extension(pebbleExtension())
                .build();
    }

    @Bean
    public AbstractExtension pebbleExtension(){
        return new BaseExtension();
    }
}
