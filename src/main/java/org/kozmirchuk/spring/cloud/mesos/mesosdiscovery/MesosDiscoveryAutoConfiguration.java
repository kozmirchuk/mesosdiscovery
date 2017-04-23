package org.kozmirchuk.spring.cloud.mesos.mesosdiscovery;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
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
        return Feign
                .builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(MesosClient.class, properties.getHost());

    }

}

