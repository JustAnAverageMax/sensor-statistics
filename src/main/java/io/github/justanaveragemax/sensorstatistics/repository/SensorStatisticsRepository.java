package io.github.justanaveragemax.sensorstatistics.repository;

import io.github.justanaveragemax.sensorstatistics.entity.SensorStatistics;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorStatisticsRepository extends JpaRepository<SensorStatistics, Long> {

  Page<SensorStatistics> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

}
