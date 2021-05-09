package com.haya.boarding.shared.util.fechas.application;

import java.util.*;

public class FechasUtil {

  /**
   * Construye una instancia de Calendario local y le suma el número de días recibido como parámetro
   *
   * @param fechaInicial El Date inicial
   * @param numDiasSumar El número de días a sumar
   * @return El Date resultante
   */
  public static Date sumarDias(Date fechaInicial, int numDiasSumar) {
    Calendar calendar = Calendar.getInstance(new Locale("es", "ES"));

    calendar.setTime(fechaInicial);
    calendar.add(Calendar.DATE, numDiasSumar);

    return calendar.getTime();
  }

  /**
   * Construye una instancia del Calendario local y devuelve la fecha actual
   *
   * @return La fecha actual
   */
  public static Date hoy() {
    Calendar calendar = Calendar.getInstance(new Locale("es", "ES"));

    return calendar.getTime();
  }

  /**
   * Método para calcular la diferencia de días entre dos fechas.
   *
   * @param fecha1
   * @param fecha2
   * @return Número de días entre las fecha1 y fecha2
   */
  public static Long diasNaturalesEntreFechas(Date fecha1, Date fecha2) {
    if (fecha1 == null || fecha2 == null) return 0L;
    int milisecondsByDay = 86400000;
    return (fecha1.getTime() - fecha2.getTime()) / milisecondsByDay;
  }

}
