package topica.nmd.abtesting.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import topica.nmd.abtesting.model.Test;
import topica.nmd.abtesting.repository.TestRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class TestRepositoryImpl implements TestRepository {
  private static final String KEY = "Test";

  private RedisTemplate<String, Object> redisTemplate;
  private HashOperations<String, Long, Test> hashOperations;

  @Autowired
  public TestRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @PostConstruct
  private void init() {
    hashOperations = redisTemplate.opsForHash();
  }

  public void save(Test test) {
    hashOperations.put(KEY, test.getId(), test);
  }

  public void saveListTest(List<Test> test) {
    for(int i=0; i < test.size(); i++){
      save(test.get(i));
    }
  }

  public Test find(Long id) {
    return hashOperations.get(KEY, id);
  }
}
