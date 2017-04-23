package org.kozmirchuk.spring.cloud.mesos.client;

import lombok.Data;

import java.util.List;

/**
 * Created by kozmirchuk on 4/23/17.
 */
@Data
public class Tasks {

    private final List<RawTask> tasks;

}
