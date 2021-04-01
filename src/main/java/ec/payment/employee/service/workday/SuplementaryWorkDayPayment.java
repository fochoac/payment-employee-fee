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
        this.duration = dayOfWeekWithTime.getDuration();
        this.dayOfWeek = dayOfWeekWithTime.getDayOfWeek();
    }

    @Override
    protected Duration getDuration() {
        return this.duration;
    }

    @Override
    protected BigDecimal getMonetaryValueByHour() {
        if (dayOfWeek.query(isWeekend())) {
            return BigDecimal.valueOf(25);
        }
        return BigDecimal.valueOf(20);
    }
}
