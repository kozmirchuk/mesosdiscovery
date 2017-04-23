package org.kozmirchuk.spring.cloud.mesos.mesosdiscovery.discovery;

import org.kozmirchuk.spring.cloud.mesos.client.MesosClient;
import org.kozmirchuk.spring.cloud.mesos.client.Port;
import org.kozmirchuk.spring.cloud.mesos.client.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MesosDiscoveryClient implements DiscoveryClient {


    private Logger logger = LoggerFactory.getLogger(MesosDiscoveryClient.class);
    private final MesosClient client;
    private final MesosDiscoveryClientProperties properties;

    public MesosDiscoveryClient(MesosClient client, MesosDiscoveryClientProperties properties) {
        this.client = client;
        this.properties = properties;
    }

    @Override
    public String description() {
        return "Spring Cloud Mesos Discovery Client";
    }

    @Override
    public ServiceInstance getLocalServiceInstance() {
        return null;
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {
        try {
            return client
                    .getTasks()
                    .stream()
                    .filter(task -> task.getName().equals(serviceId) && !task.getPort().isEmpty())
                    .map(task -> {

                        List<Port> ports = task.getPort();
                        int port = -1;
                        if(ports.size() == 1) {
                            port = ports.get(0).getPort();
                        } else {
                            /*TODO: implement choosing port that depends on serviceId pattern */
                            port = ports.get(0).getPort();
                        }

                        return new DefaultServiceInstance(task.getName(), task.getHostname(), port, false);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getServices() {
        try {
            return client
                    .getTasks()
                    .stream()
                    .map(Task::getName)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
