package io.github.justanaveragemax.sensorstatistics.mapper;

import io.github.justanaveragemax.sensorstatistics.dto.response.SensorResponse;
import io.github.justanaveragemax.sensorstatistics.dto.response.SensorStatisticsResponse;
import io.github.justanaveragemax.sensorstatistics.entity.SensorStatistics;
import java.util.List;

public interface SensorStatisticsMapper {

  SensorStatistics toEntity(List<SensorResponse> sensors);
  SensorStatisticsResponse toSensorStatisticsResponse(SensorStatistics sensorStatistics);

}
