package io.github.justanaveragemax.sensorstatistics.entity;

import io.github.justanaveragemax.sensorstatistics.entity.SensorTypeCount.SensorTypeCountId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "sensor_type_counts")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SensorTypeCountId.class)
public class SensorTypeCount {

  @Id
  @ManyToOne
  @JoinColumn(name = "statistics_id", nullable = false)
  private SensorStatistics sensorStatistics;

  @Id
  @Column(name = "sensor_type", nullable = false)
  private String type;

  @Column(name = "count", nullable = false)
  private Integer count;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SensorTypeCountId implements Serializable{

    private Long sensorStatistics;
    private String type;

  }
}