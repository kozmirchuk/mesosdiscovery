package org.kozmirchuk.spring.cloud.mesos.client;


import feign.RequestLine;

import java.util.List;

public interface MesosClient {

    @RequestLine("GET /tasks")
    List<Object> getTasks();

}
