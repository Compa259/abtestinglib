package topica.nmd.abtesting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class ConfirmationType implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String value;
}
