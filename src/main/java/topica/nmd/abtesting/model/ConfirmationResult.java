package topica.nmd.abtesting.model;

import lombok.Data;
import topica.nmd.abtesting.dto.AccountRequestDTO;

@Data
public class ConfirmationResult {
  private Long id;

  private AccountRequest accountRequest;

  private TestCase testCase;

  private Confirmation confirmation;

  public ConfirmationResult(AccountRequestDTO accountRequestDTO, Long confimationId) {
    AccountRequest accountRequest = new AccountRequest(accountRequestDTO);
    TestCase testCase = new TestCase(accountRequestDTO.getTestCaseId());
    Confirmation confirmation = new Confirmation(confimationId);

    this.accountRequest = accountRequest;
    this.testCase = testCase;
    this.confirmation = confirmation;
  }
}
