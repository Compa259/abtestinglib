package topica.nmd.abtesting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TestCase implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private Float value;
  private String name;
  private String description;
  private String payload;
  private List<Confirmation> confirmations;

  public TestCase(Long id) {
    this.id = id;
  }
}
