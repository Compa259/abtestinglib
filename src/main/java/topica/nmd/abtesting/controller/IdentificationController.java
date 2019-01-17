package topica.nmd.abtesting.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import topica.nmd.abtesting.model.Client;
import topica.nmd.abtesting.service.ClientIdFactory;
import topica.nmd.abtesting.service.RestAPIService;

@RestController
public class IdentificationController {
  @Autowired
  ClientIdFactory clientIdFactory;

  @Autowired
  RestAPIService restAPIService;

  @GetMapping("/api/check")
  public Client generateABTestingClientId(){
    return clientIdFactory.generateClientId();
  }

  @PostMapping("/api/submitTrackingUser")
  public void submitTrackingUserPassedTestCase(@RequestBody String payload){
    restAPIService.submitTrackingUserPassedTestCase(payload);
  }

  @PostMapping("/api/updateTrackingUser")
  public void updateTracking(@RequestBody String payload){
    restAPIService.updateTrackingUser(payload);
  }
}
