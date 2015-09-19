package com.youama.nexus.core.system;

import com.youama.nexus.core.Log;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class has package level, it can not be used from outside the system package. The MavenUtil purpose is to find
 * and read the POM and get the bean configuration XML file names. The POM file can be inside a JAR file in the standard
 * META-INF folder or in the development project's root directory.
 * 
 * @author David Belicza
 * @since 2015.08.22.
 */
final class MavenUtil {

	/**
	 * It retrieves the modules configuration bean XML file names. It reads the starter application's or module's POM 
	 * file and collect the artifact IDs from <i>dependencies</i>. It can calculate the possible bean configuration file
	 * names by artifact IDs.
	 * 
	 * @return The bean XML configuration file names in array String.
	 */
    static String[] getModuleConfigBeanFiles() {
        try {

            Model pomModel = getPrimaryModulePomModel();

            String primaryModuleConfigBeanXML = getPrimaryModuleConfigBeanXMLFileName();
            List<String> childrenBeans = getChildrenModuleBeanXMLs(pomModel.getModules());
            List<String> dependencyBeans = getDependencyModuleBeanXMLs(pomModel.getDependencies());

            int beansCount = ((primaryModuleConfigBeanXML != null) ? 1 : 0) + childrenBeans.size() + dependencyBeans.size();
            int j = 0;
            String[] beans = new String[beansCount];

            if (primaryModuleConfigBeanXML != null) {
                beans[0] = primaryModuleConfigBeanXML;
                j++;
            }

            if (childrenBeans.size() > 0) {
                for (String childrenBean : childrenBeans) {
                    beans[j] = childrenBean;
                    j++;
                }
            }

            if (dependencyBeans.size() > 0) {
                for (String dependencyBean : dependencyBeans) {
                    beans[j] = dependencyBean;
                    j++;
                }
            }

            return beans;
        } catch (IOException | XmlPullParserException | ArrayIndexOutOfBoundsException e) {
            Log.warning(e);
        }

        return null;
    }

    /**
     * It retrieves the primary POM model what initiated the Nexus Engine.
     * 
     * @return The POM model of the primary module what initiated the Nexus.
     * @throws IOException
     * @throws XmlPullParserException
     * @throws ArrayIndexOutOfBoundsException
     */
    private static Model getPrimaryModulePomModel()
            throws IOException, XmlPullParserException, ArrayIndexOutOfBoundsException {

        InputStream pomInputStream = getPrimaryModulePomFileInputStream();
        MavenXpp3Reader reader = new MavenXpp3Reader();
        return reader.read(pomInputStream);
    }

    /**
     * It finds and reads the POM file of the primary module inside the JAR or in the development project then it 
     * retrieves the module's POM in input stream.
     * 
     * @return The primary module's POM input stream.
     */
    private static InputStream getPrimaryModulePomFileInputStream() {

        String[] packages = Configuration.getInstance().getRegisteredMainClass().getPackage().getName().split("\\.");
        String pomFilePath = FileSystemUtil.DS + "META-INF" + FileSystemUtil.DS + "maven" + FileSystemUtil.DS
                + packages[0] + "." + packages[1] + "." + packages[2] + FileSystemUtil.DS
                + Configuration.getInstance().getRegisteredPrimaryModuleArtifactId() + FileSystemUtil.DS + "pom.xml";

        InputStream pomInputStreamJar = Configuration.getInstance().getRegisteredMainClass().getResourceAsStream(
                pomFilePath);

        if (pomInputStreamJar != null) {
            return pomInputStreamJar;
        } else {
            try {
                Path paths = Paths.get(Configuration.getInstance().getRegisteredMainClass().getProtectionDomain()
                        .getCodeSource().getLocation().toURI());

                return new FileInputStream(paths.getParent().getParent().toString() + FileSystemUtil.DS + "pom.xml");

            } catch (URISyntaxException | FileNotFoundException e) {
                Log.warning(e);
            }
        }

        return null;
    }

    /**
     * It retrieves the primary module's configuration bean XML file name. This primary module initiated the whole
     * Nexus Engine.
     * 
     * @return The primary module's configuration bean XML file name.
     */
    private static String getPrimaryModuleConfigBeanXMLFileName() {
        String[] primaryModuleArtifactIdParts =
                Configuration.getInstance().getRegisteredPrimaryModuleArtifactId().split("\\-");
        return primaryModuleArtifactIdParts[1] + "-" + primaryModuleArtifactIdParts[2] + ".xml";
    }

    /**
     * It retrieves a list of the children modules' configuration bean XML file names by children Maven modules' 
     * artifact IDs.
     * 
     * @param modules Children Maven modules' artifact IDs.
     * @return List of the children modules' configuration bean XML file names.
     */
    private static List<String> getChildrenModuleBeanXMLs(List<String> modules) {
        List<String> configBeans = new ArrayList<String>();

        for (String module : modules) {
            String[] dependModuleNamePartsLevel1 = module.split("\\/");
            String[] dependModuleNamePartsLevel2 = dependModuleNamePartsLevel1[1].split("\\-");
               configBeans.add(dependModuleNamePartsLevel1[0] + "-" + dependModuleNamePartsLevel2[1]);
        }

        return configBeans;
    }

    /**
     * It retrieves a list of the dependent modules' configuration bean XML file names by Maven Dependency list.
     * 
     * @param dependencies Maven Dependency list.
     * @return List of the dependent modules' configuration bean XML file names.
     */
    private static List<String> getDependencyModuleBeanXMLs(List<Dependency> dependencies) {
        List<String> configBeans = new ArrayList<String>();

        for (Dependency dependency : dependencies) {
            if (SystemConstant.ACCEPTED_DEPENDENCY_PACKAGE.equals(dependency.getGroupId())) {
                String[] dependModuleNameParts = dependency.getArtifactId().split("\\-");
                configBeans.add(dependModuleNameParts[1] + "-" + dependModuleNameParts[2] + ".xml");
            }
        }

        return configBeans;
    }
}
