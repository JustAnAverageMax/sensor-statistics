package io.github.justanaveragemax.sensorstatistics.service;

import io.github.justanaveragemax.sensorstatistics.dto.response.SensorResponse;
import java.util.List;

public interface SensorFetchingService {

  List<SensorResponse> fetchAllSensors();

}
