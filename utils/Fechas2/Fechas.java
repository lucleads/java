import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class FechasUtil {

  // ----------------
  public Date getPrimerDiaAnnio() {

    // Obtenemos el inicio del año actual
    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));

    return this.getPrimerDiaAnnio(cal.YEAR);
  }

  public Date getPrimerDiaAnnio(Integer annio) {

    // Obtenemos el inicio del año actual
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, annio);
    cal.set(Calendar.MONTH, 0); // 0 = January
    cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));
    resetHoursInitial(cal);

    return cal.getTime();
  }

  public Date getUltimoDiaDelAnnio(Integer annio) {

    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, annio);
    cal.set(Calendar.MONTH, 11);
    cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
    resetHoursEnd(cal);

    return cal.getTime();
  }

  public Date getYTDEnd() {
    return getFechaActual();
  }

  public Date getMesAnteriorInitial() {

    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    int mesAnterior = cal.get(Calendar.MONTH) - 1;

    // Obtenemos el inicio del mes anterior
    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
    cal.set(Calendar.MONTH, mesAnterior); // 0 = January
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
    resetHoursInitial(cal);

    return cal.getTime();
  }

  public Date getInicioUltimaSemanaMesEnCurso() {

    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    Date ultimoDiaDelMes = cal.getTime();
    ZonedDateTime zonedDateTime = ultimoDiaDelMes.toInstant().atZone(ZoneId.systemDefault());
    ZonedDateTime startOfLastWeek = zonedDateTime.minusWeeks(1).with(DayOfWeek.MONDAY);
    ZonedDateTime endOfLastWeek = startOfLastWeek.plusDays(7);
    resetHoursInitial(cal);

    return Date.from(endOfLastWeek.toInstant());
  }

  public Date getFinUltimaSemanaMesEnCurso() {
    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    resetHoursEnd(cal);

    return cal.getTime();
  }

  public Date getMesAnteriorEnd() {
    
    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    int mesAnterior = cal.get(Calendar.MONTH) - 1;

    // Obtenemos el inicio del mes anterior
    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
    cal.set(Calendar.MONTH, mesAnterior); // 0 = January
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    resetHoursInitial(cal);

    return cal.getTime();
  }

  public Date getMesEnCursoInitial() {

    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
    resetHoursInitial(cal);

    return cal.getTime();
  }

  public Date getInicioDeAnnio() {
    // Cogemos la fecha actual
    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
    cal.set(Calendar.MONTH, 0);
    cal.set(Calendar.DAY_OF_YEAR, 0);
    resetHoursInitial(cal);

    return cal.getTime();
  }

  public Date getFinDeAnnio() {

    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
    cal.set(Calendar.MONTH, 11);
    cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
    resetHoursEnd(cal);

    return cal.getTime();
  }

  public Date getInitalDateMonth(Integer annio) {

    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    cal.set(annio, Calendar.JANUARY, 1, 0, 0, 0);
    resetHoursInitial(cal);

    return cal.getTime();
  }

  public Date getFinDateMonth(Integer annio) {

    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    cal.set(annio, Calendar.DECEMBER, 31, 23, 59, 59);
    resetHoursEnd(cal);

    return cal.getTime();
  }

  public Date sumarDiasAFecha(Date fecha, int dias) {
    if (dias == 0) return fecha;
    Calendar calendar = Calendar.getInstance(new Locale("es", "ES"));
    calendar.setTime(fecha);
    calendar.add(Calendar.DAY_OF_YEAR, dias);
    resetHoursEnd(calendar);
    return calendar.getTime();
  }

  public Date restarDias(Date fecha1, long dias) {
    long milisecondsByDay = 86400000;
    return new Date((fecha1.getTime() - (dias * milisecondsByDay)));
  }

  public long restarDias(Date fecha1, Date fecha2) {
    if (fecha1 == null || fecha2 == null) return 0;
    int milisecondsByDay = 86400000;
    if ((fecha1.getTime() - fecha2.getTime()) <= milisecondsByDay) return 1;
    return (fecha1.getTime() - fecha2.getTime()) / milisecondsByDay;
  }
}
