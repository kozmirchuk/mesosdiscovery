package org.kozmirchuk.spring.cloud.mesos.client;

import lombok.Data;

import java.util.List;

@Data
public class Tasks {

    private final List<RawTask> tasks;

}
