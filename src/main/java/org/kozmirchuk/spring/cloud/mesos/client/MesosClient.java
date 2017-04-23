package org.kozmirchuk.spring.cloud.mesos.client;


import feign.Feign;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MesosClient {

    @RequestLine("GET /tasks")
    Tasks getRawTasks();

    @RequestLine("GET /slaves")
    Slaves getSlaves();


    default List<Task> getTasks() {

        Map<String, String> slaves = getSlaves()
            .getSlaves()
            .stream()
            .collect(Collectors.toMap(Slave::getId, Slave::getHostname));

        return getRawTasks().getTasks()
            .stream()
            .map(rawTask -> new Task(rawTask.getName(), slaves.get(rawTask.getSlaveId()), rawTask.getPorts()))
            .collect(Collectors.toList());

    }




    public static void main(String[] args) {
        MesosClient client = Feign
            .builder()
            .encoder(new JacksonEncoder())
            .decoder(new JacksonDecoder())
            .target(MesosClient.class, "http://dcos.darkside.cec.lab.emc.com/mesos");

        List<Task> tasks = client.getTasks();

        System.out.println(tasks);
    }

}
