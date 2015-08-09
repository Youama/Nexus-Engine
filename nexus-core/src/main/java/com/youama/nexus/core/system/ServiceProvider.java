package com.youama.nexus.core.system;

import com.youama.nexus.core.entity.version.VersionService;

import javassist.bytecode.ExceptionsAttribute;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
        /*AnnotationConfigApplicationContext configContext = new AnnotationConfigApplicationContext(new String[]{"nexus-scheduler"});
        String[] c = configContext.getBeanDefinitionNames();
        for (String ci : c) {
            System.out.println(ci);
        }*/
        context = new ClassPathXmlApplicationContext(getResourceEntityDeclarations());
        System.out.println(context);
    }

    Object getService(Class classType) {
        return context.getBean(classType);

    }

    private String[] getResourceEntityDeclarations() {
        String[] location = new String[1];

        location[0] = "ResourceEntity.xml";

        return location;
    }
}
