package com.haya.boarding.shared;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Diccionario {
  @Id
  @GeneratedValue
  @Column(name = "ID")
  protected Integer id;

  @Column(name = "CODIGO")
  protected String codigo;

  @Column(name = "DESCRIPCION")
  protected String descripcion;

  @Column(name = "DESCRIPCION_LARGA")
  protected String descripcionLarga;
}
