package io.github.justanaveragemax.sensorstatistics.controller.impl;

import io.github.justanaveragemax.sensorstatistics.controller.SensorStatisticsApi;
import io.github.justanaveragemax.sensorstatistics.dto.response.SensorStatisticsResponse;
import io.github.justanaveragemax.sensorstatistics.schedule.SensorStatisticsScheduler;
import io.github.justanaveragemax.sensorstatistics.service.SensorStatisticsService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SensorStatisticsController implements SensorStatisticsApi {

  private final SensorStatisticsService sensorStatisticsService;
  private final SensorStatisticsScheduler sensorStatisticsScheduler;

  @Override
  public ResponseEntity<PagedModel<SensorStatisticsResponse>> getStatistics(LocalDate startDate, LocalDate endDate, Pageable pageable) {
    return ResponseEntity.ok(sensorStatisticsService.getStatistics(startDate, endDate, pageable));
  }

  @Override
  public ResponseEntity<Void> fetchStatistics() {
    sensorStatisticsScheduler.collectStatistics();
    return ResponseEntity.noContent().build();
  }
}
