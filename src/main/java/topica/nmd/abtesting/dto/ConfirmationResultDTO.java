package topica.nmd.abtesting.dto;

import lombok.Data;

@Data
public class ConfirmationResultDTO {
  private String clientId;
  private Long testCaseId;
  private Long confirmationId;

  public ConfirmationResultDTO(String clientId, Long testCaseId, Long confirmationId) {
    this.clientId = clientId;
    this.testCaseId = testCaseId;
    this.confirmationId = confirmationId;
  }
}
