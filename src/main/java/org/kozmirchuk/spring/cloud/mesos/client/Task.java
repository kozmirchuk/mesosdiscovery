package org.kozmirchuk.spring.cloud.mesos.client;

import lombok.Data;

import java.util.List;

/**
 * Created by kozmirchuk on 4/23/17.
 */
@Data
public final class Task {

    private final String name;

    private final String hostname;

    private final List<Port> port;

}
