package ec.payment.employee.service.workday;

import ec.payment.employee.model.DayOfWeekWithTime;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;

public class SuplementaryWorkDayPayment extends PaymentFees {
    private final Duration duration;
    private final DayOfWeek dayOfWeek;

    public SuplementaryWorkDayPayment(Duration duration, DayOfWeek dayOfWeek) {
        this.duration = duration;
        this.dayOfWeek = dayOfWeek;
    }

    public SuplementaryWorkDayPayment(DayOfWeekWithTime dayOfWeekWithTime) {
        this(dayOfWeekWithTime.getDuration(), dayOfWeekWithTime.getDayOfWeek());
    }

    @Override
    protected DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    @Override
    protected BigDecimal getMonetaryValueByHourInMonday2Friday() {
        return BigDecimal.valueOf(20L);
    }

    @Override
    protected BigDecimal getMonetaryValueByHourInWeekend() {
        return BigDecimal.valueOf(25);
    }

    @Override
    protected Duration getDuration() {
        return this.duration;
    }


}
