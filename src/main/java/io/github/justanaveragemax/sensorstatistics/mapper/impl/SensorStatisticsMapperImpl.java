package io.github.justanaveragemax.sensorstatistics.mapper.impl;

import io.github.justanaveragemax.sensorstatistics.dto.response.SensorResponse;
import io.github.justanaveragemax.sensorstatistics.dto.response.SensorStatisticsResponse;
import io.github.justanaveragemax.sensorstatistics.entity.SensorStatistics;
import io.github.justanaveragemax.sensorstatistics.entity.SensorTypeCount;
import io.github.justanaveragemax.sensorstatistics.mapper.SensorStatisticsMapper;
import io.github.justanaveragemax.sensorstatistics.util.ConstantsUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class SensorStatisticsMapperImpl implements SensorStatisticsMapper {

  @Override
  public SensorStatistics toEntity(@NonNull final List<SensorResponse> sensors) {
    final Map<String, Long> countsByType = sensors.stream()
        .collect(Collectors.groupingBy(
            SensorResponse::type, Collectors.counting()
        ));

    final SensorStatistics sensorStatistics = SensorStatistics.builder()
        .date(LocalDate.now(ConstantsUtil.DEFAULT_ZONE_ID))
        .totalSensors(sensors.size())
        .build();

    final List<SensorTypeCount> typeCounts = countsByType.entrySet().stream()
        .map(entry -> SensorTypeCount.builder()
            .type(entry.getKey())
            .count(entry.getValue().intValue())
            .sensorStatistics(sensorStatistics)
            .build())
        .toList();

    sensorStatistics.setSensorTypeCounts(typeCounts);

    return sensorStatistics;
  }

  @Override
  public SensorStatisticsResponse toSensorStatisticsResponse(@NonNull final SensorStatistics sensorStatistics) {
    final Map<String, Integer> counts = sensorStatistics.getSensorTypeCounts().stream()
        .collect(Collectors.toMap(
            SensorTypeCount::getType, SensorTypeCount::getCount
        ));
    return SensorStatisticsResponse.builder()
        .date(sensorStatistics.getDate())
        .totalSensors(sensorStatistics.getTotalSensors())
        .sensorTypeCounts(counts)
        .build();
  }
}
