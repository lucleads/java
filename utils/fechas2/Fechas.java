import com.boarding.content.festivos_nacionales.domain.FestivosNacionales;
import com.boarding.content.festivos_nacionales.infrastructure.repository.jpa.FestivosNacionalesRepositoryJpa;
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
    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
    cal.set(Calendar.MONTH, 0); // 0 = January
    cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
    resetHoursInitial(cal);

    return cal.getTime();
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
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(mesAnterior));
    resetHoursInitial(cal);

    return cal.getTime();
  }

  public Date getMesAnteriorInitialBueno() {

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

  public Date getFecha1970() {

    // Obtenemos el inicio del mes anterior
    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    cal.set(Calendar.YEAR, 1970);
    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH - 1)); // 0 = January
    cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_MONTH) - 1);
    resetHoursInitial(cal);

    return cal.getTime();
  }

  public Date getFecha2099() {

    // Obtenemos el inicio del mes anterior

    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    cal.set(Calendar.YEAR, 2099);
    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH - 1)); // 0 = January
    cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_MONTH) - 1);
    resetHoursEnd(cal);

    return cal.getTime();
  }

  public Date getMesAnteriorEnd() {
    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    // Obtenemos el fin del mes anterior
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
    resetHoursEnd(cal);

    return cal.getTime();
  }

  public Date getMesAnteriorEndBueno() {
    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    int mesAnterior = cal.get(Calendar.MONTH) - 1;

    // Obtenemos el inicio del mes anterior
    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
    cal.set(Calendar.MONTH, mesAnterior); // 0 = January
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    resetHoursInitial(cal);

    return cal.getTime();
  }

  public Date getMesAnteriorEndDayEnd() {
    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    // Obtenemos el fin del mes anterior
    cal.set(Calendar.MONTH, Calendar.MONTH - 1);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    resetHoursEnd(cal);

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

  public Date getMesEnCursoEnd() {
    return getFechaActual();
  }

  public Date getPrevisionMesEnCursoInitial() {

    // MODIFICADO POR CONVERSACIÓN CON CHEMA, 7/2/2021-13:47:00
    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
    resetHoursInitial(cal);

    return cal.getTime();
  }

  public void resetHoursInitial(Calendar cal) {
    cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
    cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
    cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
    cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
  }

  public void resetHoursEnd(Calendar cal) {
    cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR));
    cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
    cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
    cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
  }

  public Date resetHoursEnd(Date fecha) {
    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    cal.setTime(fecha);
    cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR));
    cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
    cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
    cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));

    return cal.getTime();
  }

  public Date getPrevisionMesEnCursoEnd() {

    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    resetHoursEnd(cal);

    return cal.getTime();
  }

  public Date getPrevisionMesSiguienteInitial() {

    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));

    int mesSiguiente = cal.get(Calendar.MONTH) + 1;
    int sumarAnnio = 0;
    if (mesSiguiente > 12) {
      mesSiguiente = 0;
      sumarAnnio++;
    }

    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + sumarAnnio);
    cal.set(Calendar.MONTH, mesSiguiente);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
    resetHoursInitial(cal);

    return cal.getTime();
  }

  public Date getPrevisionMesSiguienteEnd() {

    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));

    int mesSiguiente = cal.get(Calendar.MONTH) + 1;
    int sumarAnnio = 0;
    if (mesSiguiente > 12) {
      mesSiguiente = 0;
      sumarAnnio++;
    }

    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + sumarAnnio);
    cal.set(Calendar.MONTH, mesSiguiente);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    resetHoursEnd(cal);

    return cal.getTime();
  }

  public Date getPrevisionMesSiguienteEndEndDay() {

    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));

    int mesSiguiente = cal.get(Calendar.MONTH) + 1;
    int sumarAnnio = 0;
    if (mesSiguiente > 12) {
      mesSiguiente = 0;
      sumarAnnio++;
    }

    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + sumarAnnio);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
    cal.set(Calendar.MONTH, mesSiguiente);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    //  resetHoursEnd(cal);

    return cal.getTime();
  }

  // ----------------

  public Date getFechaActual() {

    // Cogemos la fecha actual
    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
    cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR));
    resetHoursEnd(cal);

    return cal.getTime();
  }

  public Date getFechaActualPrimerasHorasDelDia() {

    // Cogemos la fecha actual
    Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
    cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR));
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

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  class Results {
    public Date date1;
    public Date date2;
    public int result;
  }

  private static List<Results> results = new ArrayList<>();
  private static List<FestivosNacionales> allResults = null;

  private FestivosNacionales getByAnnioMesDia(
      FestivosNacionalesRepositoryJpa festivosNacionalesRepositoryJpa,
      int year,
      int month,
      int day) {
    if (allResults == null) allResults = festivosNacionalesRepositoryJpa.findAll();

    return allResults.stream()
        .filter(
            result ->
                result.getAnio().equals(year)
                    && result.getMes().equals(month)
                    && result.getDia().equals(day))
        .findFirst()
        .orElse(null);
  }

  public int obtenerDiasHabiles(
      Date fecha1, Date fecha2, FestivosNacionalesRepositoryJpa festivosNacionalesRepositoryJpa) {

    if (fecha1 == null || fecha2 == null) return 2000;

    Calendar calendar1 = Calendar.getInstance(new Locale("es", "ES"));
    Calendar calendar2 = Calendar.getInstance(new Locale("es", "ES"));
    calendar1.setTime(fecha2);
    calendar2.setTime(fecha1);
    resetHoursInitial(calendar2);
    resetHoursInitial(calendar1);

    if (calendar2.getTime().getTime() > calendar1.getTime().getTime()) {
      return 2000;
    }

    Integer cachedResult =
        results.stream()
            .filter(r -> r.date1.equals(fecha1) && r.date2.equals(fecha2))
            .map(Results::getResult)
            .findAny()
            .orElse(null);
    if (cachedResult != null) return cachedResult;

    int diasHabiles = 0;

    do {
      calendar2.add(Calendar.DAY_OF_MONTH, 1);

      if (Calendar.SATURDAY != calendar2.get(Calendar.DAY_OF_WEEK)
          && Calendar.SUNDAY != calendar2.get(Calendar.DAY_OF_WEEK)) {
        if (getByAnnioMesDia(
                festivosNacionalesRepositoryJpa,
                calendar2.get(Calendar.YEAR),
                calendar2.get(Calendar.MONTH) + 1,
                calendar2.get(Calendar.DAY_OF_MONTH))
            == null) {
          diasHabiles++;
        }
      }

      if (calendar2.get(Calendar.YEAR) == calendar1.get(Calendar.YEAR)
          && calendar2.get(Calendar.MONTH) == calendar1.get(Calendar.MONTH)
          && calendar2.get(Calendar.DAY_OF_MONTH) == calendar1.get(Calendar.DAY_OF_MONTH)) {
        if (Calendar.SATURDAY != calendar2.get(Calendar.DAY_OF_WEEK)
            && Calendar.SUNDAY != calendar2.get(Calendar.DAY_OF_WEEK)) {
          if (getByAnnioMesDia(
                  festivosNacionalesRepositoryJpa,
                  calendar2.get(Calendar.YEAR),
                  calendar2.get(Calendar.MONTH) + 1,
                  calendar2.get(Calendar.DAY_OF_MONTH))
              == null) {
            diasHabiles++;
          }
        }
        break;
      }
    } while (calendar2.getTime().getTime() < calendar1.getTime().getTime());

    results.add(new Results(fecha1, fecha2, diasHabiles));
    return diasHabiles;
  }

  public int obtenerDiasHabiles(
      Date fecha, FestivosNacionalesRepositoryJpa festivosNacionalesRepositoryJpa) {
    Date fecha2 = getFechaActual();

    return obtenerDiasHabiles(fecha, fecha2, festivosNacionalesRepositoryJpa);
  }

  public int obtenerDiasHabilesDosFechas(
      Date fecha, Date fecha2, FestivosNacionalesRepositoryJpa festivosNacionalesRepositoryJpa) {

    return obtenerDiasHabiles(fecha, fecha2, festivosNacionalesRepositoryJpa);
  }
}
