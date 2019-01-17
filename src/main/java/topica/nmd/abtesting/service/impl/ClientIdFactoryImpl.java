package topica.nmd.abtesting.service.impl;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import topica.nmd.abtesting.dto.ClientId;
import topica.nmd.abtesting.model.Client;
import topica.nmd.abtesting.service.ClientIdFactory;

@Service
@Slf4j
public class ClientIdFactoryImpl implements ClientIdFactory {
  RestTemplate restTemplate = new RestTemplate();

  //private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  private static int LENGTH = 36;
  @Override
  public Client generateClientId() {
    return new Client(randomString());
  }

  @Override
  public Client submitTracking(Client client) {
    return null;
  }

  @Override
  public void updateTracking(String payload) {
    Gson gson = new Gson();
    ClientId clientId = gson.fromJson(payload, ClientId.class);
    System.out.println("AAA: " + clientId.getClientId());
    System.out.println("AAA: " + clientId.getTempId());
  }

  private String randomString() {
    String result =null;
    final String charset = ALPHA_NUMERIC_STRING;
    final int size = LENGTH;

    for (int i = 0; i < size; i++) {
      StringBuilder builder = new StringBuilder();

      int length = LENGTH;

      while (length-- != 0) {
        int character = (int) (Math.random() * charset.length());
        builder.append(charset.charAt(character));
      }
      result = builder.toString().trim();
    }

    return result;
  }
}
