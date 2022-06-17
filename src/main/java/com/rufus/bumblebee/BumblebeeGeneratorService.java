package com.rufus.bumblebee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Objects;
import java.util.concurrent.Executor;

import static java.lang.Integer.parseInt;

@SpringBootApplication(scanBasePackages = {"com.rufus.bumblebee.*"})
@EnableAsync
public class BumblebeeGeneratorService {

    private final Environment env;

    public static final String KEY_UID = "CONTAINER_UID";
    public static final String KEY_STATUS = "CONTAINER_STATUS";
    public static final String KEY_GENERATORS_SIZE = "GENERATORS_SIZE";
    public static final String KEY_CONTAINER_NAME = "CONTAINER_NAME";

    public BumblebeeGeneratorService(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        SpringApplication.run(BumblebeeGeneratorService.class, args);
    }

    @Bean
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(
                parseInt(Objects.requireNonNull(env.getProperty("thread-pool.core-size")))
        );
        executor.setMaxPoolSize(
                parseInt(Objects.requireNonNull(env.getProperty("thread-pool.max-size")))
        );
        executor.setThreadNamePrefix("Async- ");
        return executor;
    }
}
