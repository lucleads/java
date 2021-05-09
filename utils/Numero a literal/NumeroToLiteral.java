package com.haya.boarding.shared.util;

public class NumeroToLiteral {
  public static String numToLiteral(double num_i1) {
    return numToLiteral(num_i1, "M");
  }

  public static String numToLiteral(double num_i1, String sexoMoneda) {
    String strDecimales = "" + num_i1;
    int p = strDecimales.indexOf('.');
    if (p >= 0) {
      p = strDecimales.length() - p - 1;
      if (p > 3) p = 3;
      strDecimales = "";
      for (int n = 0; n < p; n++) strDecimales += "0";
    }

    int num_i;
    int num_i2;
    String num_c = "";
    int mill = 0;
    int div1, div111;

    num_i = (int) num_i1;
    num_i2 = (int) num_i1;
    if (num_i == 0) num_c = "CERO ";
    if (num_i > 1000000000 || num_i < -1000000000) return null;

    if (num_i < 0) {
      num_c = " MENOS ";
      num_i = num_i * -1;
      num_i2 = (num_i2 * -1);
    }
    if (num_i >= 1000000) {
      mill = 1;
      div111 = num_i / 1000000;
      div1 = div111;
      if (div111 == 1) num_c = num_c.trim() + " UN MILLÓN";
      else
        num_c =
            num_c.trim() + " " + letra(div1, mill, sexoMoneda, num_i, false).trim() + " MILLONES";
      num_i = num_i - div111 * 1000000;
      if (num_i == 0) return num_c;
    }
    mill = 0;
    if (num_i >= 1000) {
      div111 = num_i / 1000;
      div1 = div111;
      if (div1 == 1 && num_c == null) num_c = " MIL";
      else {
        String numMiles = letra(div1, mill, sexoMoneda, num_i, true).trim();
        numMiles = numMiles.matches("UN[OA]") ? "" : numMiles;
        num_c = num_c.trim() + " " + numMiles + " MIL";
      }
      num_i = num_i - div111 * 1000;
      if (num_i == 0) return num_c;
    }
    if (num_i > 0) {
      div1 = num_i;
      num_c = num_c.trim() + " " + letra(div1, mill, sexoMoneda, num_i, false).trim();
    }
    num_i1 = Math.abs(num_i1);
    if (num_i1 - num_i2 != 0) {
      div1 =
          (int)
              (((num_i1 - num_i2) + Double.parseDouble("0." + strDecimales + "1"))
                  * Integer.parseInt("1" + strDecimales));
      num_c = num_c.trim() + " CON " + letra(div1, mill, sexoMoneda, num_i, false).trim();
    }
    return num_c;
  }

  static String letra(int div1, int mill, String sexoMoneda, int numOriginal, boolean miles) {
    int div11;
    String num_c1 = "";

    if (div1 == 100) {
      num_c1 = " CIEN";
      return num_c1;
    }

    if (div1 > 100) {
      div11 = div1 / 100;
      num_c1 = cientos(div11, sexoMoneda);
      div1 = div1 - div11 * 100;
    }

    if (div1 >= 10) {
      div11 = div1 / 10;
      num_c1 = num_c1.trim() + decen(div1, div11, mill, sexoMoneda, numOriginal, miles);
      div1 = div1 - div11 * 10;
      return num_c1;
    }
    num_c1 = num_c1.trim() + tr_unid(div1, mill, sexoMoneda, miles);
    return num_c1;
  }

  /**
   * Pasa un numero a cientos de de ese numero. Recibe: Un integer de 1 a 9 Devuelve: Cadena con los
   * cientos que sea.
   */
  static String cientos(int div11, String sexoMoneda) {
    String pes1 = "";

    switch (div11) {
      case 1:
        pes1 = " CIENTO ";
        break;
      case 2:
        pes1 = " DOSCIENT";
        break;
      case 3:
        pes1 = " TRESCIENT";
        break;
      case 4:
        pes1 = " CUATROCIENT";
        break;
      case 5:
        pes1 = " QUINIENT";
        break;
      case 6:
        pes1 = " SEISCIENT";
        break;
      case 7:
        pes1 = " SETECIENT";
        break;
      case 8:
        pes1 = " OCHOCIENT";
        break;
      case 9:
        pes1 = " NOVECIENT";
    }
    if (div11 != 1)
      if (sexoMoneda.equals("M")) pes1 += "OS ";
      else pes1 += "AS ";
    return pes1;
  }

  /** Recibe un numero entre 1 y 9 devuelve la cadena con su representacion en decenas. */
  static String decen(int div1, int div11, int mill, String sexoMoneda, int numOriginal, boolean miles) {

    String pes1 = "";

    switch (div11) {
      case 1:
        pes1 = tr_dece(div1);
        break;
      case 2:
        if (div1 == 20) {
          pes1 = " VEINTE ";
          break;
        }
        div1 = div1 - div11 * 10;
        String val = tr_unid(div1, mill, sexoMoneda, miles);
        val = (val.matches("UN[OA] ") && numOriginal > 1000) ? "ÚN" : val;
        pes1 = " VEINTI" + val;
        break;
      case 3:
        if (div1 == 30) {
          pes1 = " TREINTA ";
          break;
        }
        div1 = div1 - div11 * 10;
        pes1 = " TREINTA Y " + tr_unid(div1, mill, sexoMoneda, miles);
        break;
      case 4:
        if (div1 == 40) {
          pes1 = " CUARENTA ";
          break;
        }
        div1 = div1 - div11 * 10;
        pes1 = " CUARENTA Y " + tr_unid(div1, mill, sexoMoneda, miles);
        break;
      case 5:
        if (div1 == 50) {
          pes1 = " CINCUENTA ";
          break;
        }
        div1 = div1 - div11 * 10;
        pes1 = " CINCUENTA Y " + tr_unid(div1, mill, sexoMoneda, miles);
        break;
      case 6:
        if (div1 == 60) {
          pes1 = " SESENTA ";
          break;
        }
        div1 = div1 - div11 * 10;
        pes1 = " SESENTA Y " + tr_unid(div1, mill, sexoMoneda, miles);
        break;
      case 7:
        if (div1 == 70) {
          pes1 = " SETENTA ";
          break;
        }
        div1 = div1 - div11 * 10;
        pes1 = " SETENTA Y " + tr_unid(div1, mill, sexoMoneda, miles);
        break;
      case 8:
        if (div1 == 80) {
          pes1 = " OCHENTA ";
          break;
        }
        div1 = div1 - div11 * 10;
        pes1 = " OCHENTA Y " + tr_unid(div1, mill, sexoMoneda, miles);
        break;
      case 9:
        if (div1 == 90) {
          pes1 = " NOVENTA ";
          break;
        }
        div1 = div1 - div11 * 10;
        pes1 = " NOVENTA Y " + tr_unid(div1, mill, sexoMoneda, miles);
    }
    return pes1;
  }

  /** Funcion de tratar las decenas. */
  static String tr_dece(int div1) {
    String pes1 = "";

    switch (div1) {
      case 10:
        pes1 = " DIEZ ";
        break;
      case 11:
        pes1 = " ONCE ";
        break;
      case 12:
        pes1 = " DOCE ";
        break;
      case 13:
        pes1 = " TRECE ";
        break;
      case 14:
        pes1 = " CATORCE ";
        break;
      case 15:
        pes1 = " QUINCE ";
        break;
      case 16:
        pes1 = " DIECISEIS ";
        break;
      case 17:
        pes1 = " DIECISIETE ";
        break;
      case 18:
        pes1 = " DIECIOCHO ";
        break;
      case 19:
        pes1 = " DIECINUEVE ";
        break;
    }

    return pes1;
  }

  /**
   * Pasa un numero a literal en unidades. Recibe: Un numero entre uno y 9 Devuelve: Cadena con la
   * representancion.
   */
  static String tr_unid(int div1, int mill, String sexoMoneda, boolean miles) {
    String pes1 = "";

    switch (div1) {
      case 1:
        if (mill == 0) {
          pes1 = "UN ";
          if (sexoMoneda.equals("F")) pes1 = pes1.trim() + "A ";
          else if (!miles) pes1 = pes1.trim() + "O ";
        }
        break;
      case 2:
        pes1 = "DOS ";
        break;
      case 3:
        pes1 = "TRES ";
        break;
      case 4:
        pes1 = "CUATRO ";
        break;
      case 5:
        pes1 = "CINCO ";
        break;
      case 6:
        pes1 = "SEIS ";
        break;
      case 7:
        pes1 = "SIETE ";
        break;
      case 8:
        pes1 = "OCHO ";
        break;
      case 9:
        pes1 = "NUEVE ";
    }
    return pes1;
  }
}
