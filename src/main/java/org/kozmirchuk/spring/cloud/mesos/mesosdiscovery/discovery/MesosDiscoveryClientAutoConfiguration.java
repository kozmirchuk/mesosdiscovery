package org.kozmirchuk.spring.cloud.mesos.mesosdiscovery.discovery;

import org.kozmirchuk.spring.cloud.mesos.client.MesosClient;
import org.kozmirchuk.spring.cloud.mesos.mesosdiscovery.ConditionalOnMesosDiscoveryEnabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMesosDiscoveryEnabled
@ConditionalOnProperty(value = "spring.cloud.mesosdiscovery.client.enabled", matchIfMissing = true)
@EnableConfigurationProperties
public class MesosDiscoveryClientAutoConfiguration {

    private final MesosClient client;

    @Autowired
    public MesosDiscoveryClientAutoConfiguration(MesosClient client) {
        this.client = client;
    }

    @Bean
    public MesosDiscoveryClientProperties mesosDiscoveryClientProperties() {
        return new MesosDiscoveryClientProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public MesosDiscoveryClient mesosDiscoveryClient() {
        return new MesosDiscoveryClient(client, mesosDiscoveryClientProperties());
    }

}
