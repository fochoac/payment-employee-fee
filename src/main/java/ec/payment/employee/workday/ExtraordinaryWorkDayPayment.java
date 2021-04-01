package ec.payment.employee.workday;

import ec.payment.employee.model.DayOfWeekWithTime;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;

public class ExtraordinaryWorkDayPayment extends PaymentFees {
    private final Duration duration;
    private final DayOfWeek dayOfWeek;

    public ExtraordinaryWorkDayPayment(Duration duration, DayOfWeek dayOfWeek) {
        this.duration = duration;
        this.dayOfWeek = dayOfWeek;
    }

    public ExtraordinaryWorkDayPayment(DayOfWeekWithTime dayOfWeekWithTime) {
        this.duration = dayOfWeekWithTime.getDuration();
        this.dayOfWeek = dayOfWeekWithTime.getDayOfWeek();
    }

    @Override
    protected BigDecimal getMonetaryValueByHour() {
        if (dayOfWeek.query(isWeekend())) {
            return BigDecimal.valueOf(30);
        }
        return BigDecimal.valueOf(25);
    }

    @Override
    protected Duration getDuration() {
        return this.duration;
    }
}
