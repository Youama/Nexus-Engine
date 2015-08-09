package com.youama.nexus.core.entity.version;

import java.util.List;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.02.
 */
public interface Version {

    public void save(VersionEntity versionEntity);

    public List<VersionEntity> getList();
}
