package topica.nmd.abtesting.model;

import lombok.Data;
import topica.nmd.abtesting.dto.AccountRequestDTO;

@Data
public class AccountRequest {
  private Long id;
  private String accountId;
  private TestCase testCase;

  public AccountRequest(String accountId, TestCase testCase) {
    this.accountId = accountId;
    this.testCase = testCase;
  }

  public AccountRequest(AccountRequestDTO accountRequestDTO){
    this.id = accountRequestDTO.getId();
  }
}
