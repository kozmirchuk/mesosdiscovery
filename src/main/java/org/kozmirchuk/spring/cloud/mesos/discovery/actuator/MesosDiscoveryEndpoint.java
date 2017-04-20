package org.kozmirchuk.spring.cloud.mesos.discovery.actuator;

import lombok.Data;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "endpoints.mesosdiscovery", ignoreUnknownFields = false)
public class MesosDiscoveryEndpoint  extends AbstractEndpoint<MesosDiscoveryEndpoint.MesosDiscoveryData> {


    public MesosDiscoveryEndpoint() {
        super("mesosdiscovery", false, true);
    }

    @Override
    public MesosDiscoveryData invoke() {
        return null;
    }

    @Data
    public static class MesosDiscoveryData {
    }
}
