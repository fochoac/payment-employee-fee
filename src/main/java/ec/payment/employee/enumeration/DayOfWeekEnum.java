package ec.payment.employee.enumeration;

import java.time.DayOfWeek;
import java.util.stream.Stream;

public enum DayOfWeekEnum {
    MONDAY("MO", DayOfWeek.MONDAY),
    TUESDAY("TU", DayOfWeek.TUESDAY),
    WEDNESDAY("WE", DayOfWeek.WEDNESDAY),
    THURSDAY("TH", DayOfWeek.THURSDAY),
    FRIDAY("FR", DayOfWeek.FRIDAY),
    SATURDAY("SA", DayOfWeek.SATURDAY),
    SUNDAY("SU", DayOfWeek.SUNDAY);

    private final String codeDayOfWeek;
    private final DayOfWeek dayOfWeek;

    DayOfWeekEnum(String codeDayOfWeek, DayOfWeek dayOfWeek) {
        this.codeDayOfWeek = codeDayOfWeek;
        this.dayOfWeek = dayOfWeek;
    }

    public static final DayOfWeekEnum getDayOfWeekEnumBy(String codeDayOfWeek) {
        return Stream.of(DayOfWeekEnum.values())
                .filter(value -> value.codeDayOfWeek.equals(codeDayOfWeek))
                .findFirst()
                .orElseThrow(() ->
                        new UnsupportedOperationException("The code of the day of week doesn't exists. Please verify the data. Cod: " + codeDayOfWeek))
                ;
    }

    public String getCodeDayOfWeek() {
        return codeDayOfWeek;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
