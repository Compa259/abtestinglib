package topica.nmd.abtesting.service;

import topica.nmd.abtesting.dto.AccountRequestDTO;
import topica.nmd.abtesting.dto.ConfirmationDTO;
import topica.nmd.abtesting.dto.TestResponse;
import topica.nmd.abtesting.model.*;

import java.util.List;

public interface RestAPIService {
  Test getTest(long id);
  Test checkRequestBelongToTest(String request);
  void getAllTest();
  TestResponse getTestList(int pageIndex);
  ProcessedTestResponse getProcessedTest(Test test);
  void getTestPerMinute();
  void saveUserTracking(String accountId, TestCase id);
  List<Confirmation> getConfirmationByValue(String value);
  List<Confirmation> getConfirmationByTestCaseId(Long id);
  List<AccountRequestDTO> getAccountRequestByAccountIdAndTestCaseId(String accountId, Long testCaseId);
  void saveTrackingUserPassedTestCase(ConfirmationResult confirmationResult);
  void saveTrackingUser(AccountRequest accountRequest);
  void updateTrackingUser(String payload);
  void submitTrackingUserPassedTestCase(String payload);
}
