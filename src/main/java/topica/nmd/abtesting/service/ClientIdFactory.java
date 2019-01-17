package topica.nmd.abtesting.service;

import topica.nmd.abtesting.model.Client;

public interface ClientIdFactory {
  Client generateClientId();
  Client submitTracking(Client client);
  void updateTracking(String payload);
}
