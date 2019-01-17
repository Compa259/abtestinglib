package topica.nmd.abtesting.repository;

import topica.nmd.abtesting.model.Test;

import java.util.List;

public interface TestRepository {
  void save(Test test);
  void saveListTest(List<Test> test);
  Test find(Long id);
}
