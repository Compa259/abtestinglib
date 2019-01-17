package topica.nmd.abtesting.model;

import lombok.Data;
import topica.nmd.abtesting.constant.MetrixType;
import topica.nmd.abtesting.constant.TestStatusType;
import topica.nmd.abtesting.constant.TestType;

@Data
public class ProcessedTestResponse {
  private Long id;

  private String name;

  private String description;

  private TestStatusType status;

  private MetrixType metrixType;

  private TestType type;

  private Long createdAt;

  private Long updatedAt;

  private Admin creatingAdmin;

  private Admin updatingAdmin;

  private TestCase testCase;

  public ProcessedTestResponse(Test test) {
    this.id = test.getId();
    this.name = test.getName();
    this.description = test.getDescription();
    this.status = test.getStatus();
    this.metrixType = test.getMetrixType();
    this.createdAt = test.getCreatedAt();
    this.updatedAt = test.getUpdatedAt();
    this.creatingAdmin = test.getCreatingAdmin();
    this.updatingAdmin = test.getUpdatingAdmin();
    this.testCase = null;
  }

  public ProcessedTestResponse(Test test, int indexTestCase) {
    this.id = test.getId();
    this.name = test.getName();
    this.description = test.getDescription();
    this.status = test.getStatus();
    this.metrixType = test.getMetrixType();
    this.createdAt = test.getCreatedAt();
    this.updatedAt = test.getUpdatedAt();
    this.creatingAdmin = test.getCreatingAdmin();
    this.updatingAdmin = test.getUpdatingAdmin();
//    this.testCase = test.getTestCases().get(indexTestCase);
  }

}
