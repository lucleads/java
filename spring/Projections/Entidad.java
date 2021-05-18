package com.boarding.content.entidad.domain;

import lombok.NoArgsConstructor;
import lombok.Getter;


@Entity
@NoArgsConstructor
@Table(name = "MSTR_ENTIDAD")
public class Entidad {
  @Id
  @GeneratedValue
  @Getter
  @Column(name = "ID")
  private int id;
  
  @Getter
  @Column(name = "NOMBRE")
  private String nombre;
  
  @Column(name = "CAMPO_EXTRA")
  private String campoExtra;
}
