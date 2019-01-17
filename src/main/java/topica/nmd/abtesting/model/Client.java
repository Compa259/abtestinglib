package topica.nmd.abtesting.model;

import lombok.Data;

@Data
public class Client {
  private String clientId;

  public Client(String clientId) {
    this.clientId = clientId;
  }
}
