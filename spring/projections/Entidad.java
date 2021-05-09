package com.haya.boarding.content.entidad.domain;

import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Table(name = "MSTR_ENTIDAD")
public class Entidad {
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private int id;
  
  @Column(name = "NOMBRE")
  private String nombre;
  
  @Column(name = "CAMPO_EXTRA")
  private String campoExtra;
}
