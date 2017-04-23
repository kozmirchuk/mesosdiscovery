package org.kozmirchuk.spring.cloud.mesos.mesosdiscovery;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;


@ConfigurationProperties("spring.cloud.mesos.mesosdiscovery")
@Data
public class MesosDiscoveryProperties {

    @NotNull
    private String scheme = "http";

    @NotNull
    private String host = "localhost";

    @NotNull
    private int port = 5050;
}



