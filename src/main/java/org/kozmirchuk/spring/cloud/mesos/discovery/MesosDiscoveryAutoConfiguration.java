package org.kozmirchuk.spring.cloud.mesos.discovery;

import feign.Feign;
import org.kozmirchuk.spring.cloud.mesos.client.MesosClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties
@ConditionalOnMesosDiscoveryEnabled
public class MesosDiscoveryAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public MesosDiscoveryProperties mesosDiscoveryProperties() {
        return new MesosDiscoveryProperties();
    }


    @Bean
    @ConditionalOnMissingBean
    public MesosClient mesosClient(MesosDiscoveryProperties properties) {
        return Feign.builder().target(MesosClient.class, properties.getHost());
    }

}

