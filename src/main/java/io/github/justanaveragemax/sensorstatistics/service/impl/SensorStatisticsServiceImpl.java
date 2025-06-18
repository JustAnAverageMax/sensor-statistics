package io.github.justanaveragemax.sensorstatistics.service.impl;

import io.github.justanaveragemax.sensorstatistics.dto.response.SensorResponse;
import io.github.justanaveragemax.sensorstatistics.dto.response.SensorStatisticsResponse;
import io.github.justanaveragemax.sensorstatistics.entity.SensorStatistics;
import io.github.justanaveragemax.sensorstatistics.mapper.SensorStatisticsMapper;
import io.github.justanaveragemax.sensorstatistics.repository.SensorStatisticsRepository;
import io.github.justanaveragemax.sensorstatistics.service.SensorStatisticsService;
import io.github.justanaveragemax.sensorstatistics.validation.validator.DateRangeValidator;
import java.time.LocalDate;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SensorStatisticsServiceImpl implements SensorStatisticsService {

  private final SensorStatisticsRepository sensorStatisticsRepository;
  private final SensorStatisticsMapper sensorStatisticsMapper;
  private final DateRangeValidator dateRangeValidator;

  @Override
  @Transactional
  public void saveStatistics(@NonNull final List<SensorResponse> sensors) {
    log.info("Mapping {} sensors to sensor statistics", sensors.size());
    SensorStatistics sensorStatistics = sensorStatisticsMapper.toEntity(sensors);

    log.info("Saving new sensor statistics with total sensor amount {}", sensorStatistics.getTotalSensors());
    sensorStatistics = sensorStatisticsRepository.save(sensorStatistics);
    log.info("Sensor statistics successfully saved with id '{}'", sensorStatistics.getId());
  }

  @Override
  @Transactional(readOnly = true)
  public PagedModel<SensorStatisticsResponse> getStatistics(final LocalDate start,
                                                            final LocalDate end,
                                                            @NonNull final Pageable pageable) {
    log.info("Validating parameters: start={}, end={}", start, end);
    dateRangeValidator.validateDateRange(start, end);

    log.info("Fetching sensor statistics with parameters: start={}, end={}, page={}, size={}",
        start,
        end,
        pageable.getPageNumber(),
        pageable.getPageSize());

    Page<SensorStatistics> sensorStatistics =
        start == null || end == null ?
            sensorStatisticsRepository.findAll(pageable) :
            sensorStatisticsRepository.findByDateBetween(start, end, pageable);
    log.info("Found {} sensor statistics", sensorStatistics.getNumberOfElements());

    return new PagedModel<>(sensorStatistics.map(sensorStatisticsMapper::toSensorStatisticsResponse));
  }
}
