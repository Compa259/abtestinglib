package topica.nmd.abtesting.filter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import topica.nmd.abtesting.constant.TestStatusType;
import topica.nmd.abtesting.dto.ABTestingData;
import topica.nmd.abtesting.dto.AccountRequestDTO;
import topica.nmd.abtesting.model.*;
import topica.nmd.abtesting.service.ClientIdFactory;
import topica.nmd.abtesting.service.RestAPIService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResponseFilter implements Filter {
  @Autowired
  RestAPIService restAPIService;

  @Autowired
  ClientIdFactory clientIdFactory;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    try {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      String url = httpRequest.getRequestURL().toString();
      String param = httpRequest.getParameter("abtesting");

//      Cookie[] cookies = httpRequest.getCookies();

//      if(cookies != null){
//        for(Cookie ck: cookies){
//          System.out.println("TEN CUA COOKIE");
//          System.out.println(ck.getName());
//        }
//      }else{
//        System.out.println("KHONG CO COOKIE");
//      }

      HtmlResponseWrapper capturingResponseWrapper = new HtmlResponseWrapper((HttpServletResponse) response);
      chain.doFilter(request, capturingResponseWrapper);

      Test test = restAPIService.checkRequestBelongToTest(url);
//
//      Confirmation confirmation = checkRequestSatisfyTestCase(url);
//      if(confirmation != null){
//        List<AccountRequestDTO> accountRequestDTOS = getAccountRequestId("", confirmation.getTestCaseId());
//        if(accountRequestDTOS != null){
//          saveUserTrackingPassedTestcase(accountRequestDTOS.get(0), confirmation.getId());
//        }
//      }

      if (test != null && test.getStatus() == TestStatusType.ACTIVATED) {
        ProcessedTestResponse processedTestResponse = restAPIService.getProcessedTest(test);


        if (response.getContentType() != null && response.getContentType().contains("application/json")) {
          //generate clientId
          Client client = clientIdFactory.generateClientId();

          //create object Account Request
          AccountRequest accountRequest = new AccountRequest(client.getClientId(), processedTestResponse.getTestCase());
          // save tracking user who's in test case
          restAPIService.saveTrackingUser(accountRequest);

          //Get confirmation from test_case_id
          List<Confirmation> confirmationList = restAPIService.getConfirmationByTestCaseId(processedTestResponse.getTestCase().getId());
          List<Long> listConfirmationId = new ArrayList<>();
          if(confirmationList.size() > 0) {
            for(int i = 0; i < confirmationList.size(); i++){
              listConfirmationId.add(confirmationList.get(i).getId());
            }
          }

          ABTestingData abTestingData = new ABTestingData(processedTestResponse, null, null, listConfirmationId, client);

          String content = capturingResponseWrapper.getCaptureAsString();
          JSONObject source = new JSONObject(content);

          GsonBuilder builder = new GsonBuilder();
          Gson gson = builder.serializeNulls().create();
          String abtesting = gson.toJson(abTestingData);

          appendABTestingData(source, abtesting);

          response.getWriter().write(source.toString());
        }
      } else {
        if (response.getContentType() != null && response.getContentType().contains("application/json")) {
          String content = capturingResponseWrapper.getCaptureAsString();
          response.setContentLength(content.length());
          response.getWriter().write(content);
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void destroy() {

  }

  private JSONObject appendABTestingData(JSONObject source, String extra) {
    try {
      source.put("abtesting", new JSONObject(extra));
    } catch (JSONException e) {
      e.printStackTrace();
    }finally {
      return source;
    }
  }

  private Confirmation checkRequestSatisfyTestCase(String url){
    List<Confirmation> confirmationList = restAPIService.getConfirmationByValue(url);
    if(!confirmationList.isEmpty()){
      return confirmationList.get(0);
    }
    return null;
  }

  private List<AccountRequestDTO> getAccountRequestId(String accountId, Long testCaseId){
    List<AccountRequestDTO> accountRequestDTOS = restAPIService.getAccountRequestByAccountIdAndTestCaseId(accountId, testCaseId);
    return accountRequestDTOS;
  }

  private void saveUserTrackingPassedTestcase(AccountRequestDTO accountRequestDTO, Long confirmationId){
    ConfirmationResult confirmationResult = new ConfirmationResult(accountRequestDTO, confirmationId);
    restAPIService.saveTrackingUserPassedTestCase(confirmationResult);
  }
}
