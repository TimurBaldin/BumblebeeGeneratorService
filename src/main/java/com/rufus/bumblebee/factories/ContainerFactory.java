package com.rufus.bumblebee.factories;

import com.rufus.bumblebee.container.TestDataContainer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ContainerFactory {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public TestDataContainer getContainer() {
        return new TestDataContainer();
    }

}
