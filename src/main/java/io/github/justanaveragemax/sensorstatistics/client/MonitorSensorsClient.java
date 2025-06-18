package io.github.justanaveragemax.sensorstatistics.client;

import io.github.justanaveragemax.sensorstatistics.config.FeignClientConfiguration;
import io.github.justanaveragemax.sensorstatistics.dto.response.PagedResponse;
import io.github.justanaveragemax.sensorstatistics.dto.response.SensorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    name = "monitor-sensors",
    url = "${clients.monitor-sensors.url}",
    configuration = FeignClientConfiguration.class
)
public interface MonitorSensorsClient {

  @GetMapping("/sensors")
  PagedResponse<SensorResponse> getSensors(@SpringQueryMap Pageable pageable);

}
