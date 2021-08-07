package com.rufus.bumblebee.services;

import com.google.gson.Gson;
import com.rufus.bumblebee.controllers.requests.ReportType;
import com.rufus.bumblebee.repository.tables.Container;
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

    public void sendTestDataToReportService(List<Map<String, List<String>>> data, Container container) {
        KafkaDto dto = new KafkaDto();
        dto.setContainerId(container.getId());
        dto.setContainerName(container.getName());
        dto.setAuthenticated(container.getAuthenticated());
        dto.setReportType(container.getType());
        dto.setData(data);

        template.send(topic.name(), gson.toJson(dto));
    }

    private class KafkaDto {
        private Long containerId;
        private String containerName;
        private Boolean isAuthenticated;
        private ReportType reportType;
        private List<Map<String, List<String>>> data;

        public Long getContainerId() {
            return containerId;
        }

        public void setContainerId(Long containerId) {
            this.containerId = containerId;
        }

        public String getContainerName() {
            return containerName;
        }

        public void setContainerName(String containerName) {
            this.containerName = containerName;
        }

        public Boolean getAuthenticated() {
            return isAuthenticated;
        }

        public void setAuthenticated(Boolean authenticated) {
            isAuthenticated = authenticated;
        }

        public ReportType getReportType() {
            return reportType;
        }

        public void setReportType(ReportType reportType) {
            this.reportType = reportType;
        }

        public List<Map<String, List<String>>> getData() {
            return data;
        }

        public void setData(List<Map<String, List<String>>> data) {
            this.data = data;
        }
    }

}
