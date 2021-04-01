package ec.payment.employee.model;

import ec.payment.employee.enumeration.DayOfWeekEnum;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DayOfWeekWithTime {
    private final DayOfWeekEnum dayOfWeekEnum;
    private final LocalTime startTime;
    private final LocalTime endTime;


    public DayOfWeekWithTime(String dayOfWeekWithTime) {
        final String dayOfWeekInitials = dayOfWeekWithTime.substring(0, 2);
        final String[] times = dayOfWeekWithTime.substring(2).split("-");
        this.startTime = LocalTime.parse(times[0]);
        if (startTime.compareTo(LocalTime.MIDNIGHT) == 0) {
            throw new UnsupportedOperationException("The start time is invalid: " + startTime.toString());
        }
        this.endTime = LocalTime.parse(times[1]);
        this.dayOfWeekEnum = DayOfWeekEnum.getDayOfWeekEnumBy(dayOfWeekInitials);
    }

    public Duration getDuration() {
        if (this.endTime.compareTo(LocalTime.MIDNIGHT) == 0) {
            return Duration.between(this.startTime, LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS)).plusMinutes(1L);
        }
        return Duration.between(this.startTime, this.endTime);
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeekEnum.getDayOfWeek();
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return dayOfWeekEnum.getCodeDayOfWeek() + this.startTime.toString() + "-" + this.endTime.toString();
    }
}
