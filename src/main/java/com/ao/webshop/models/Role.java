package com.ao.webshop.models;

import com.ao.webshop.models.dto.Dto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class Role implements Dto {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String roleName;

  public Role(String roleName) {
    this.roleName = roleName;
  }
}
