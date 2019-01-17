package topica.nmd.abtesting.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import topica.nmd.abtesting.constant.MetrixType;
import topica.nmd.abtesting.constant.TestStatusType;
import topica.nmd.abtesting.constant.TestType;

import java.io.Serializable;
import java.util.List;

@Data
public class Test implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String description;

  private TestStatusType status;

  private MetrixType metrixType;

  private TestType type;

  private Long createdAt;

  private Long updatedAt;

  private Admin creatingAdmin;

  private Admin updatingAdmin;
}
