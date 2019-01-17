package topica.nmd.abtesting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.io.Serializable;


@Data
public class Confirmation implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String value;
  private ConfirmationLevel confirmationLevel;
  private ConfirmationType confirmationType;
  private Long testCaseId;

  public Confirmation(Long id) {
    this.id = id;
  }
}
