package com.youama.nexus.core.system;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

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


    static String[] getModuleConfigFiles() {
        List<String> configBeans = new ArrayList<String>();

        String[] moduleNameParts = ConfigurationLocalUtil.initModuleId.split("\\-");
        String parentDirectory = "";
        if ("app".equals(moduleNameParts[1])) {
            parentDirectory = "apps";
        } else {
            parentDirectory = "modules";
        }

        if (beanExists(
                moduleNameParts[1] + "-" + moduleNameParts[2],
                moduleNameParts[0] + "-" + moduleNameParts[2],
                parentDirectory
        )) {
            configBeans.add(moduleNameParts[1] + "-" + moduleNameParts[2] + ".xml");
        }

        String path = FileSystemUtil.getBaseDirectory() + FileSystemUtil.DS + parentDirectory + FileSystemUtil.DS +
                moduleNameParts[0] + "-" + moduleNameParts[2] + FileSystemUtil.DS + "pom.xml";

        MavenXpp3Reader reader = new MavenXpp3Reader();

        try {
            Model model = reader.read(new FileReader(path));

            List<String> modules = model.getModules();
            for (String module : modules) {
                String[] dependModuleNamePartsLevel1 = module.split("\\/");
                String[] dependModuleNamePartsLevel2 = dependModuleNamePartsLevel1[1].split("\\-");
                if (beanExists(
                        dependModuleNamePartsLevel1[0] + "-" + dependModuleNamePartsLevel2[1],
                        dependModuleNamePartsLevel2[0] + "-" + dependModuleNamePartsLevel2[1],
                        parentDirectory
                )) {
                    configBeans.add(dependModuleNamePartsLevel1[0] + "-" + dependModuleNamePartsLevel2[1]);
                }
            }

            List<Dependency> dependencies = model.getDependencies();
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        String[] beans = new String[configBeans.size()];
        for (int i = 0; i < configBeans.size(); i++) {
            beans[i] = configBeans.get(i);
        }

        return beans;
    }

    private static boolean beanExists(String beanFileName, String moduleRootDirectory, String moduleType) {
        File bean = new File(FileSystemUtil.getBaseDirectory() + FileSystemUtil.DS + moduleType + FileSystemUtil.DS +
                moduleRootDirectory + FileSystemUtil.DS + "src" + FileSystemUtil.DS + "main" + FileSystemUtil.DS +
                "resources" + FileSystemUtil.DS + beanFileName + ".xml");

        if (bean.exists()) {
            return true;
        } else {
            return false;
        }
    }

    private static String getScope(String scopeName) {
        if ("app".equals(scopeName)) {
            return "apps";
        } else {
            return "modules";
        }
    }
}
