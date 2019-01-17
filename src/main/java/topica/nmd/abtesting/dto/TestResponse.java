package topica.nmd.abtesting.dto;

import lombok.Data;
import topica.nmd.abtesting.model.Test;

import java.util.List;

@Data
public class TestResponse {
  private List<Test> data;
  private PageDTO page;
}
