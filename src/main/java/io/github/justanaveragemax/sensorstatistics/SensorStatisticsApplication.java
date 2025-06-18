package io.github.justanaveragemax.sensorstatistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class SensorStatisticsApplication {

  public static void main(String[] args) {
    SpringApplication.run(SensorStatisticsApplication.class, args);
  }

}
