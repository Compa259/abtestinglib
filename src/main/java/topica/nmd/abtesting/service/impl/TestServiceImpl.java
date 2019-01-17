package topica.nmd.abtesting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import topica.nmd.abtesting.model.Test;
import topica.nmd.abtesting.repository.TestRepository;
import topica.nmd.abtesting.service.TestService;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
  @Autowired
  TestRepository testRepository;

  public void saveTest(Test test) {
    testRepository.save(test);
  }

  public void saveListTest(List<Test> testList) {
    testRepository.saveListTest(testList);
  }

  public Test findById(long id) {
    Test test = testRepository.find(id);
    return test;
  }
}
