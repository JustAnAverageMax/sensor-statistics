package io.github.justanaveragemax.sensorstatistics.service.impl;

import io.github.justanaveragemax.sensorstatistics.client.MonitorSensorsClient;
import io.github.justanaveragemax.sensorstatistics.dto.response.PagedResponse;
import io.github.justanaveragemax.sensorstatistics.dto.response.SensorResponse;
import io.github.justanaveragemax.sensorstatistics.service.SensorFetchingService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SensorFetchingServiceImpl implements SensorFetchingService {

  private static final int DEFAULT_PAGE_SIZE = 50;

  private final MonitorSensorsClient monitorSensorsClient;

  @Override
  public List<SensorResponse> fetchAllSensors() {
    final List<SensorResponse> sensors = new ArrayList<>();
    int page = 0;


    while(true){
      Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
      log.debug("Fetching sensors from monitor-sensors service. Page: {}", page);

      PagedResponse<SensorResponse> response = monitorSensorsClient.getSensors(pageable);

      if(response == null || response.getContent() == null){
        log.warn("Received null or empty response from monitor-sensors service at page {}", page);
        break;
      }

      sensors.addAll(response.getContent());
      log.debug("Fetched {} sensors from page {}", response.getContent().size(), page);

      if(response.getPage() == null || page >= response.getPage().getTotalPages() - 1){
        break;
      }

      page++;
    }

    log.info("Total sensors fetched: {}", sensors.size());

    return sensors;
  }
}
