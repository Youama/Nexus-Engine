package com.youama.nexus.core.service.scope;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.youama.nexus.core.model.scope.ScopeModel;
import com.youama.nexus.core.model.scope.ScopeTable;
import com.youama.nexus.core.system.NexusCoreUtil;
import com.youama.nexus.core.system.ServiceUtil;

/**
 * @author David Belicza
 * @since 0.1.0
 */
public class ScopeServiceTest {

    List<String> drivers = ServiceUtil.getInstalledDrivers();

    @Before
    public void setUp() {
        NexusCoreUtil.initServices("nexus-module-core", ScopeModel.class);
    }

    @Test
    public void testServiceMethods() {

        for (String driver : drivers) {
            ServiceUtil.switchDriver(driver);

            ScopeService service = (ScopeService) ServiceUtil.getService(ScopeService.class);

            ScopeModel model = new ScopeModel();
            model.setStatus(ScopeTable.VALUE_STATUS_ENABLED);
            model.setType(ScopeTable.VALUE_TYPE_DEFAULT);

            ScopeModel savedModel = service.add(model);

            assertTrue(savedModel != null && ScopeTable.VALUE_STATUS_ENABLED == savedModel.getStatus()
                    && savedModel.getScopeId() >= 0);

            System.out.println(savedModel.getScopeId());
        }
    }

    @After
    public void tierDown() {
        NexusCoreUtil.removeServices();
    }
}
