package io.github.justanaveragemax.sensorstatistics.service;

import io.github.justanaveragemax.sensorstatistics.dto.response.SensorResponse;
import io.github.justanaveragemax.sensorstatistics.dto.response.SensorStatisticsResponse;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

public interface SensorStatisticsService {

  void saveStatistics(List<SensorResponse> sensors);

  PagedModel<SensorStatisticsResponse> getStatistics(LocalDate start, LocalDate end, Pageable pageable);

}
