package topica.nmd.abtesting.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Admin implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String username;
  private String password;
  private String role;
  private Long createdAt;
  private Long updatedAt;
  private String whiteList;
}
