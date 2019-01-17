package topica.nmd.abtesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import topica.nmd.abtesting.model.Confirmation;
import topica.nmd.abtesting.model.ConfirmationLevel;
import topica.nmd.abtesting.model.ConfirmationType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConfirmationDTO {
  private Long id;

  private String value;

  private ConfirmationLevel confirmationLevel;

  private ConfirmationType confirmationType;

  private Long testCaseId;
}
