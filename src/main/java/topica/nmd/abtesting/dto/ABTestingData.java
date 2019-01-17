package topica.nmd.abtesting.dto;

import lombok.Data;
import topica.nmd.abtesting.constant.TestType;
import topica.nmd.abtesting.model.Client;
import topica.nmd.abtesting.model.ProcessedTestResponse;

import java.util.List;

@Data
public class ABTestingData {
  private long id;
  private TestType typeResponse;
  private String urlPassedTestcase;
  private String type;
  private String title;
  private String value;
  private String clientId;
  private List<Long> listConfirmationId;

  public ABTestingData(ProcessedTestResponse processedTestResponse, String type, String title, List<Long> listConfirmationId, Client client) {
    this.id = processedTestResponse.getTestCase().getId();
    this.typeResponse = processedTestResponse.getType();
    this.urlPassedTestcase = processedTestResponse.getTestCase().getDescription();
    this.type = type;
    this.title = title;
    this.listConfirmationId = listConfirmationId;
    this.value = processedTestResponse.getTestCase().getPayload();
    this.clientId = client.getClientId();
  }
}
