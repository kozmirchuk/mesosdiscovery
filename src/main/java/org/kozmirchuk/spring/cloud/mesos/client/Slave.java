package org.kozmirchuk.spring.cloud.mesos.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by kozmirchuk on 4/23/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public final class Slave {

    private final String id;
    private final String hostname;

    @JsonCreator
    public Slave(@JsonProperty("id") String id,
                 @JsonProperty("hostname") String hostname) {
        this.id = id;
        this.hostname = hostname;
    }
}
