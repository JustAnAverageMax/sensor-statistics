package io.github.justanaveragemax.sensorstatistics.repository;

import io.github.justanaveragemax.sensorstatistics.entity.SensorStatistics;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorStatisticsRepository extends JpaRepository<SensorStatistics, Long> {

  List<SensorStatistics> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
