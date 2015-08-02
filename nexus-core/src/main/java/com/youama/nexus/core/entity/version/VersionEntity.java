package com.youama.nexus.core.entity.version;

import javax.persistence.*;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.02.
 */

@Entity
@Table(name = "Version")
public class VersionEntity {

    @Id
    @Column(name="versionId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long versionId;

    private String versionKey;

    public void setVersionId(long versionId) {
        this.versionId = versionId;
    }

    public long getVersionId() {
        return this.versionId;
    }

    public void setVersionKey(String versionKey) {
        this.versionKey = versionKey;
    }

    public String getVersionKey() {
        return this.versionKey;
    }
}
