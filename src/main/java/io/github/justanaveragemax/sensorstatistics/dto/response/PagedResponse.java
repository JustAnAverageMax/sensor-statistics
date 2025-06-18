package io.github.justanaveragemax.sensorstatistics.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse<T> {

  private List<T> content;
  private PageMeta page;

  @Data
  public static class PageMeta {

    private Integer page;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;
  }

}
