package com.youama.nexus.core.system;

import com.youama.nexus.core.Log;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author David Belicza
 * @since 2015.08.22.
 */
final class MavenUtil {


    static String[] getModuleConfigBeanFiles() {
        try {
            String[] primaryModuleArtifactIdParts =
                    Configuration.getInstance().getRegisteredPrimaryModuleArtifactId().split("\\-");

            Model pomModel = null;

                pomModel = getPrimaryModulePomModel(primaryModuleArtifactIdParts);

            String primaryModuleConfigBeanXML = getPrimaryModuleConfigBeanXML(primaryModuleArtifactIdParts);
            List<String> childrenBeans = getChildrenModuleBeanXMLs(pomModel.getModules());
            List<String> dependencyBeans = getDependencyModuleBeanXMLs(pomModel.getDependencies());

            int beansCount = ((primaryModuleConfigBeanXML != null) ? 1 : 0) + childrenBeans.size() + dependencyBeans.size();
            String[] beans = new String[beansCount];

            if (primaryModuleConfigBeanXML != null) {
                beans[0] = primaryModuleConfigBeanXML;
            }

            if (childrenBeans.size() > 0) {
                int start = beans.length;
                for (int i = 0; i < childrenBeans.size(); i++) {
                    beans[start - 1 + i] = childrenBeans.get(i);
                }
            }

            if (dependencyBeans.size() > 0) {
                int start = beans.length;
                for (int i = 0; i < dependencyBeans.size(); i++) {
                    beans[start - 1 + i] = dependencyBeans.get(i);
                }
            }

            return beans;
        } catch (IOException | XmlPullParserException e) {
            Log.warning(e);
        }

        return null;
    }

    private static Model getPrimaryModulePomModel(String[] primaryModuleArtifactIdParts)
            throws IOException, XmlPullParserException {

        String parentDirectory = getScope(primaryModuleArtifactIdParts[1]);

        String path = FileSystemUtil.getBaseDirectory() + FileSystemUtil.DS + parentDirectory + FileSystemUtil.DS +
                primaryModuleArtifactIdParts[0] + "-" + primaryModuleArtifactIdParts[2] + FileSystemUtil.DS + "pom.xml";

        MavenXpp3Reader reader = new MavenXpp3Reader();

        return reader.read(new FileReader(path));
    }

    private static String getPrimaryModuleConfigBeanXML(String[] primaryModuleArtifactIdParts) {
        if (beanExists(
                primaryModuleArtifactIdParts[1] + "-" + primaryModuleArtifactIdParts[2],
                primaryModuleArtifactIdParts[0] + "-" + primaryModuleArtifactIdParts[2],
                getScope(primaryModuleArtifactIdParts[1])
        )) {
            return primaryModuleArtifactIdParts[1] + "-" + primaryModuleArtifactIdParts[2] + ".xml";
        }

        return null;
    }

    private static List<String> getChildrenModuleBeanXMLs(List<String> modules) {
        List<String> configBeans = new ArrayList<String>();

        for (String module : modules) {
            String[] dependModuleNamePartsLevel1 = module.split("\\/");
            String[] dependModuleNamePartsLevel2 = dependModuleNamePartsLevel1[1].split("\\-");
            if (beanExists(
                    dependModuleNamePartsLevel1[0] + "-" + dependModuleNamePartsLevel2[1],
                    dependModuleNamePartsLevel2[0] + "-" + dependModuleNamePartsLevel2[1],
                    getScope(dependModuleNamePartsLevel2[0])
            )) {
                configBeans.add(dependModuleNamePartsLevel1[0] + "-" + dependModuleNamePartsLevel2[1]);
            }
        }

        return configBeans;
    }

    private static List<String> getDependencyModuleBeanXMLs(List<Dependency> dependencies) {
        List<String> configBeans = new ArrayList<String>();

        for (Dependency dependency : dependencies) {
            if ("com.youama.nexus".equals(dependency.getGroupId())) {
                String[] dependModuleNameParts = dependency.getArtifactId().split("\\-");
                if (beanExists(
                        dependModuleNameParts[1] + "-" + dependModuleNameParts[2],
                        dependModuleNameParts[0] + "-" + dependModuleNameParts[2],
                        getScope(dependModuleNameParts[1])
                )) {
                    configBeans.add(dependModuleNameParts[1] + "-" + dependModuleNameParts[2] + ".xml");
                }
            }
        }

        return configBeans;
    }

    private static boolean beanExists(String beanFileName, String moduleRootDirectory, String moduleType) {
        File bean = new File(FileSystemUtil.getBaseDirectory() + FileSystemUtil.DS + moduleType + FileSystemUtil.DS +
                moduleRootDirectory + FileSystemUtil.DS + "src" + FileSystemUtil.DS + "main" + FileSystemUtil.DS +
                "resources" + FileSystemUtil.DS + beanFileName + ".xml");

        return bean.exists();
    }

    private static String getScope(String scopeName) {
        if ("app".equals(scopeName)) {
            return "apps";
        } else {
            return "modules";
        }
    }
}
