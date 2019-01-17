package topica.nmd.abtesting.service.impl;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import topica.nmd.abtesting.dto.AccountRequestDTO;
import topica.nmd.abtesting.dto.ClientId;
import topica.nmd.abtesting.dto.ConfirmationResultDTO;
import topica.nmd.abtesting.dto.TestResponse;
import topica.nmd.abtesting.model.*;
import topica.nmd.abtesting.service.RestAPIService;
import topica.nmd.abtesting.service.TestService;

import java.util.*;

@Service
@Slf4j
public class RestAPIServiceImpl implements RestAPIService {
  @Autowired
  TestService testService;

  @Value("${spring.account-request.url}")
  private String urlAccountRequest;

  @Value("${spring.confirmation-result.url}")
  private String urlConfirmationResult;

  @Value("${spring.confirmation-value.url}")
  private String urlConfirmationValue;

  @Value("${spring.test.processed.url}")
  private String urlProcessedTest;

  @Value("${spring.account-request.update.url}")
  private String urlUpdateAccountRequest;

  @Value("${spring.test.url}")
  private String urlTest;

  @Value("${spring.testcase.url}")
  private String urlTestCase;

  List<Test> listAllTest = new ArrayList<Test>();


  RestTemplate restTemplate = new RestTemplate();

  public Test getTest(long id) {
    Test test = restTemplate.getForObject(urlTest + "/" + id, Test.class);
    return test;
  }

  public TestResponse getTestList(int pageIndex) {
    TestResponse testResponse = restTemplate.getForObject(urlTest + "?pageIndex=" + pageIndex, TestResponse.class);
    return testResponse;
  }

  public void getTestPerMinute() {
    Timer t = new Timer();
    t.schedule(new TimerTask() {
      @Override
      public void run() {
        getAllTest();
      }
    }, 0, 60000);
  }

  public Test checkRequestBelongToTest(String request) {
    for(Test test: listAllTest){
      String description = test.getDescription();
      if(request.equals(description)){
        return test;
      }
    }
    return null;
  }

  public ProcessedTestResponse getProcessedTest(Test test){
    String stringResult = restTemplate.getForObject(urlProcessedTest + "/" + test.getId(), String.class);
    Gson gson = new Gson();
    ProcessedTestResponse processedTestResponse = gson.fromJson(stringResult, ProcessedTestResponse.class);
    return processedTestResponse;
  }

  @Override
  public void saveUserTracking(String accountId, TestCase testCase) {
    AccountRequest accountRequest = new AccountRequest(accountId, testCase);
    restTemplate.postForEntity(urlAccountRequest, accountRequest, AccountRequest.class);
  }

  @Override
  public List<Confirmation> getConfirmationByValue(String value) {
    Confirmation[] confirmationArray = restTemplate.getForObject(urlConfirmationValue + "?value=" +value, Confirmation[].class);
    List<Confirmation> confirmationList = Arrays.asList(confirmationArray);
    return confirmationList;
  }

  @Override
  public List<Confirmation> getConfirmationByTestCaseId(Long id) {
    Confirmation[] confirmationArray = restTemplate.getForObject(urlTestCase + "/" + id + "/confirmations", Confirmation[].class);
    List<Confirmation> confirmationList = Arrays.asList(confirmationArray);
    return confirmationList;
  }

  @Override
  public List<AccountRequestDTO> getAccountRequestByAccountIdAndTestCaseId(String accountId, Long testCaseId) {
    AccountRequestDTO[] accountRequestArray =  restTemplate.getForObject(urlAccountRequest + "?accountId=" + accountId + "&testCaseId=" + testCaseId, AccountRequestDTO[].class);
    List<AccountRequestDTO> accountRequestDTOS = Arrays.asList(accountRequestArray);
    return accountRequestDTOS;
  }

  @Override
  public void saveTrackingUserPassedTestCase(ConfirmationResult confirmationResult) {
    restTemplate.postForEntity(urlConfirmationResult, confirmationResult, ConfirmationResult.class);
  }

  @Override
  public void saveTrackingUser(AccountRequest accountRequest) {
    restTemplate.postForEntity(urlAccountRequest, accountRequest, AccountRequest.class);
  }

  @Override
  public void updateTrackingUser(String payload) {
    Gson gson = new Gson();
    ClientId clientId = gson.fromJson(payload, ClientId.class);
    System.out.println("WTF: " + clientId.getTempId());
    AccountRequestDTO[] accountRequestDTOS = restTemplate.getForObject(urlAccountRequest + "?accountId=" + "ducnm11@topica.edu.vn", AccountRequestDTO[].class);
    System.out.println("--------00000----------");

    List<Long> listAccountRequestId = new ArrayList<>();
    if(accountRequestDTOS.length > 0){
      String listAccountRequest = "";
      for(int i = 0; i < accountRequestDTOS.length ; i++) {
        listAccountRequestId.add(accountRequestDTOS[i].getTestCaseId());
        listAccountRequest += String.valueOf(accountRequestDTOS[i].getTestCaseId()) + ",";
      }
      restTemplate.getForObject(urlUpdateAccountRequest + "?accountId=" + clientId.getClientId() + "&listId=" + listAccountRequest, AccountRequest.class);
    }
  }

  @Override
  public void submitTrackingUserPassedTestCase(String payload) {
    Gson gson = new Gson();
    ConfirmationResultDTO confirmationResultDTO = gson.fromJson(payload, ConfirmationResultDTO.class);
    List<AccountRequestDTO> accountRequestDTOS = getAccountRequestByAccountIdAndTestCaseId(confirmationResultDTO.getClientId(), confirmationResultDTO.getTestCaseId());

    if(accountRequestDTOS.size() > 0){
      ConfirmationResult confirmationResult = new ConfirmationResult(accountRequestDTOS.get(0), confirmationResultDTO.getConfirmationId());
      restTemplate.postForEntity(urlConfirmationResult, confirmationResult, ConfirmationResult.class);
    }
  }

  public void getAllTest() {
    int i = 0;
    int totalPages;
    List<Test> testListTemp = new ArrayList<Test>();

    do {
      TestResponse testResponse = getTestList(i);
      totalPages = testResponse.getPage().getTotalPages();
      List<Test> testList = testResponse.getData();
      testListTemp.addAll(testList);
      testService.saveListTest(testList);
    }while (i++ < totalPages - 1);

    listAllTest = testListTemp;
  }
}
