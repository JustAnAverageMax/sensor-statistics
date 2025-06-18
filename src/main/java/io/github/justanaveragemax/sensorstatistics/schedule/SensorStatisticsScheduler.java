package io.github.justanaveragemax.sensorstatistics.schedule;

import io.github.justanaveragemax.sensorstatistics.dto.response.SensorResponse;
import io.github.justanaveragemax.sensorstatistics.service.SensorFetchingService;
import io.github.justanaveragemax.sensorstatistics.service.SensorStatisticsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SensorStatisticsScheduler {

  private final SensorFetchingService sensorFetchingService;
  private final SensorStatisticsService sensorStatisticsService;

  @Scheduled(cron = "0 0 2 * * *", zone = "Europe/Minsk")
  public void collectStatistics(){
    log.info("Starting daily sensor fetching process");

    final List<SensorResponse> sensors = sensorFetchingService.fetchAllSensors();

    sensorStatisticsService.saveStatistics(sensors);
    log.info("A total of {} sensors was successfully fetched and saved", sensors.size());
  }

}
