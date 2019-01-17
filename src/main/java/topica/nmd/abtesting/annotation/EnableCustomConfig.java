package topica.nmd.abtesting.annotation;

import org.springframework.context.annotation.Import;
import topica.nmd.abtesting.config.RedisConfig;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({
  RedisConfig.class
})
public @interface EnableCustomConfig {
}