package org.kozmirchuk.spring.cloud.mesos.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.io.IOException;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RawTask {


    private final String slaveId;

    private final String name;

    private List<Port> ports;

    @JsonCreator
    public RawTask(@JsonProperty("slave_id") String slaveId,
            @JsonProperty("name") String name) {
        this.slaveId = slaveId;
        this.name = name;
    }

    @JsonDeserialize(using = PortDeserializer.class)
    @JsonProperty("discovery")
    void setPort( List<Port> ports) {
        this.ports = ports;
    }

    private static class PortDeserializer extends JsonDeserializer<List<Port>> {

        private ObjectMapper mapper = new ObjectMapper();

        @Override
        public  List<Port> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

            TreeNode treeNode = jsonParser.readValueAsTree();

            TreeNode portList = treeNode.get("ports").get("ports");

            if(!portList.isArray())
                throw new RuntimeException();

            ObjectReader objectReader = mapper.readerFor(new TypeReference<List<Port>>() {
            });


            return objectReader.readValue(portList.toString());
        }
    }

}
