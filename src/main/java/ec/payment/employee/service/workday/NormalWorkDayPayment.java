package ec.payment.employee.service.workday;

import ec.payment.employee.model.DayOfWeekWithTime;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;

public class NormalWorkDayPayment extends PaymentFees {
    private final Duration duration;
    private final DayOfWeek dayOfWeek;

    public NormalWorkDayPayment(Duration duration, DayOfWeek dayOfWeek) {
        this.duration = duration;
        this.dayOfWeek = dayOfWeek;
    }

    public NormalWorkDayPayment(DayOfWeekWithTime dayOfWeekWithTime) {
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
            return BigDecimal.valueOf(20);
        }
        return BigDecimal.valueOf(15);
    }
}
