package com.rufus.bumblebee.services;

import com.google.gson.Gson;
import com.rufus.bumblebee.repository.tables.Container;
import lombok.Data;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KafkaService {

    private final KafkaTemplate<String, String> template;
    private final Gson gson = new Gson();
    private final NewTopic topic;

    @Autowired
    public KafkaService(KafkaTemplate<String, String> template, NewTopic topic) {
        this.template = template;
        this.topic = topic;
    }

    public void sendTestDataToReportService( List<Map<String, List<String>>> data, Container container) {
        KafkaDto dto = new KafkaDto();
        dto.setContainerId(container.getId());
        dto.setContainerName(container.getName());
        dto.setIsAuthenticated(container.getIsAuthenticated());
        dto.setData(data);

        template.send(topic.name(), gson.toJson(dto));
    }


    @Data
    private class KafkaDto {
        private Long containerId;
        private String containerName;
        private Boolean isAuthenticated;
        private List<Map<String, List<String>>> data;
    }

}
