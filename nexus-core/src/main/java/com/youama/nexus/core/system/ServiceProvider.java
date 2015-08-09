package com.youama.nexus.core.system;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.*;
import java.lang.System;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.09.
 */
final class ServiceProvider {

    private ApplicationContext context;

    void setApplicationContext() {
        context = new ClassPathXmlApplicationContext(getResourceEntityDeclarations());
    }

    Object getService(Class classType) {
        return context.getBean(classType);

    }

    private String[] getResourceEntityDeclarations() {
        return new String[] {"config.xml"};
    }
}
