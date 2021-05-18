package com.boarding.content.condicion.domain;

import com.boarding.shared.Auditable;
import lombok.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/** @author Lucas Dante Elizalde on Dic, 2020 */
@Getter
@Setter
@Entity
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LKUP_CONDICION")
public class Condicion extends Auditable<Integer> {

  @Id
  @GeneratedValue
  @Column(name = "ID")
  private Integer id;

  @Column(name = "NOMBRE")
  private String nombre;

  @Column(name = "TABLA")
  private String tabla;

  @Column(name = "CAMPO")
  private String campo;

  @Column(name = "VALOR")
  private String valor;

  @Column(name = "OPERADOR")
  private String operador;

  @Column(name = "DESCRIPCION", columnDefinition = "TEXT")
  private String descripcion;

  @Column(name = "CAPAS_DESDE_OFERTA")
  private String capasDesdeOferta;

  /**
   * Devuelve el String que compone el Hash una vez se codifique por SHA-256
   *
   * @return Los campos de la Condición concatenados
   */
  public String obtenerEntradaHash() {
    StringBuilder stringBuilder = new StringBuilder();

    return stringBuilder
        .append(this.nombre)
        .append(this.tabla)
        .append(this.campo)
        .append(this.valor)
        .append(this.operador)
        .toString();
  }
}
