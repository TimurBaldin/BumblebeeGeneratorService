package com.rufus.bumblebee.services.interfaces;

import com.rufus.bumblebee.repository.tables.Container;

public interface KafkaService<T> {

    void sendTestDataToReportService(T data, Container container);

}
