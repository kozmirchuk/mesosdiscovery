package org.kozmirchuk.spring.cloud.mesos.discovery;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

public class MesosDiscoveryClient implements DiscoveryClient {
    @Override
    public String description() {
        return null;
    }

    @Override
    public ServiceInstance getLocalServiceInstance() {
        return null;
    }

    @Override
    public List<ServiceInstance> getInstances(String s) {
        return null;
    }

    @Override
    public List<String> getServices() {
        return null;
    }
}
