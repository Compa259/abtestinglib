package topica.nmd.abtesting.dto;

import lombok.Data;

@Data
public class ClientId {
  private String clientId;
  private String tempId;

  public ClientId(String clientId, String tempId) {
    this.clientId = clientId;
    this.tempId = tempId;
  }
}
