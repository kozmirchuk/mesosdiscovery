package org.kozmirchuk.spring.cloud.mesos.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Port {

    private final int port;
    private final String name;

    @JsonCreator
    public Port(@JsonProperty("number") int port,
         @JsonProperty("name") String name) {
        this.port = port;
        this.name = name;
    }
}
