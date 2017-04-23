package org.kozmirchuk.spring.cloud.mesos.client;

import lombok.Data;

import java.util.List;

@Data
public final class Task {

    private final String name;

    private final String hostname;

    private final List<Port> port;

}
