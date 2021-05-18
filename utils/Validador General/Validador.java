package com.boarding.shared.validador;

public class Validador {

  private static String CIF_REGEX = "^([ABCDEFGHJKLMNPQRSUVW])(\\d{7})([0-9A-J])$";
  private static String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
  private static String TLFN_REGEX = "^(\\+34|0034|34)?[6789]\\d{8}$";
  private static String DNI_REGEX = "^(\\d{8})([A-Z])$";
  private static String NIE_REGEX = "^[XYZ]\\d{7,8}[A-Z]$";
  private static String PASAPORTE_REGEX = "^[a-z]{3}[0-9]{6}[a-z]?$";

  public static boolean validarCif(String cif) {
    boolean valido = false;
    if (cif.matches(CIF_REGEX)) {
      valido = true;
    }
    return valido;
  }

  public static boolean validarEmail(String email) {
    boolean valido = false;
    if (email.matches(EMAIL_REGEX)) {
      valido = true;
    }
    return valido;
  }

  public static boolean validarTlfn(String tlfn) {
    boolean valido = false;
    if (tlfn.matches(TLFN_REGEX)) {
      valido = true;
    }
    return valido;
  }

  public static boolean validarDni(String dni) {
    boolean valido = false;
    if (dni.matches(DNI_REGEX)) {
      valido = true;
    }
    return valido;
  }

  public static boolean validarNie(String nie) {
    boolean valido = false;
    if (nie.matches(NIE_REGEX)) {
      valido = true;
    }
    return valido;
  }

  public static boolean validarPasaporte(String pasaporte) {
    boolean valido = false;
    if (pasaporte.matches(PASAPORTE_REGEX)) {
      valido = true;
    }
    return valido;
  }
}
