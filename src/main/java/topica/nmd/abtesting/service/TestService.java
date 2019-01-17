package topica.nmd.abtesting.service;

import topica.nmd.abtesting.model.Test;

import java.util.List;

public interface TestService {
  void saveTest(Test test);
  void saveListTest(List<Test> testList);
  Test findById(long id);
}
