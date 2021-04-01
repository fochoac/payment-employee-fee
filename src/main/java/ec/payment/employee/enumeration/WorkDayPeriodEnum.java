package ec.payment.employee.enumeration;

import ec.payment.employee.model.DayOfWeekWithTime;

import java.time.LocalTime;

public enum WorkDayPeriodEnum {
    EXTRAORDINARY(LocalTime.parse("00:01"), LocalTime.parse("09:00")),
    NORMAL(LocalTime.parse("09:01"), LocalTime.parse("18:00")),
    SUPLEMENTARY(LocalTime.parse("18:01"), LocalTime.parse("00:00"));

    private final LocalTime startPeriod;
    private final LocalTime endPeriod;

    WorkDayPeriodEnum(LocalTime startPeriod, LocalTime endPeriod) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }


    public static boolean isNormalPeriod(DayOfWeekWithTime dayOfWeekWithTime) {

        return dayOfWeekWithTime.getStartTime().compareTo(NORMAL.startPeriod) >= 0 &&
                dayOfWeekWithTime.getEndTime().compareTo(NORMAL.endPeriod) <= 0;
    }

    public static boolean isExtraordinaryPeriod(DayOfWeekWithTime dayOfWeekWithTime) {
        return dayOfWeekWithTime.getStartTime().compareTo(EXTRAORDINARY.startPeriod) >= 0
                && dayOfWeekWithTime.getEndTime().compareTo(EXTRAORDINARY.endPeriod) <= 0;
    }

    public static boolean isSuplementaryPeriod(DayOfWeekWithTime dayOfWeekWithTime) {
        boolean isEqualOrAfterOfEighteenOne = dayOfWeekWithTime.getStartTime().compareTo(SUPLEMENTARY.startPeriod) >= 0;
        boolean isEqualOrBeforeMindNight = Boolean.FALSE;
        if (dayOfWeekWithTime.getEndTime().compareTo(LocalTime.MIDNIGHT) == 0) {
            isEqualOrBeforeMindNight = true;
        } else {
            isEqualOrBeforeMindNight = dayOfWeekWithTime.getEndTime().compareTo(LocalTime.MAX) <= 0;
        }
        return isEqualOrAfterOfEighteenOne && isEqualOrBeforeMindNight;
    }
}
