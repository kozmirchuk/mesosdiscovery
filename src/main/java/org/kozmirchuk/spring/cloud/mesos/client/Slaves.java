package org.kozmirchuk.spring.cloud.mesos.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by kozmirchuk on 4/23/17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Slaves {

    private final List<Slave> slaves;
}
