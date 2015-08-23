package com.youama.nexus.core.system;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.util.List;

/**
 * This class provides access to resource service implementations. The visibility of the methods of this SingleService
 * class are package level. These methods are not available from other packages.
 *
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.08.09.
 */
final class SingleService {

    /**
     * The application context configuration beans - like database access.
     */
    private ApplicationContext context;

    /**
     * It sets the application context configuration beans like database access. The application context scope based
     * on the location of the caller method.
     */
    void setApplicationContext() {
        context = new ClassPathXmlApplicationContext(getResourceEntityDeclarations());
    }

    /**
     * It retrieves the entity's service implementation by the service class name.
     *
     * @param classType Any class type what is a service class. Service classes should be defined as a Bean.
     * @return It is the service implementation. Return type is an Object what can be cast to any service class.
     */
    Object getService(Class classType) {
        return context.getBean(classType);

    }

    /**
     * It retrieves the configuration Bean file(s).
     *
     * @return String array of the configuration bean xml files.
     */
    private String[] getResourceEntityDeclarations() {
        return MavenUtil.getModuleConfigBeanFiles();
    }
}
