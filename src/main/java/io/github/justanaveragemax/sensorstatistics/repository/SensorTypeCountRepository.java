package io.github.justanaveragemax.sensorstatistics.repository;

import io.github.justanaveragemax.sensorstatistics.entity.SensorTypeCount;
import io.github.justanaveragemax.sensorstatistics.entity.SensorTypeCount.SensorTypeCountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorTypeCountRepository extends JpaRepository<SensorTypeCount, SensorTypeCountId> {

}
