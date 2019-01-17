package topica.nmd.abtesting.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountRequestDTO {
  private Long id;

  private String accountId;

  private Long testCaseId;
}
