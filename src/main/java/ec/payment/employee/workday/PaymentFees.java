package ec.payment.employee.workday;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalQuery;

public abstract class PaymentFees {

    public BigDecimal calculatePayment() {
        return getNumberOfMinutes()
                .multiply(getMonetaryValueByMinute())
                .setScale(2, RoundingMode.HALF_UP);
    }


    protected abstract BigDecimal getMonetaryValueByHour();

    protected abstract Duration getDuration();

    protected TemporalQuery<Boolean> isWeekend() {
        return temporal ->
                temporal.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.SATURDAY.getValue()
                        || temporal.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.SUNDAY.getValue();


    }

    private BigDecimal getMonetaryValueByMinute() {
        final double numberOfMinutesByHour = 60D;
        return BigDecimal.valueOf(getMonetaryValueByHour().doubleValue() / numberOfMinutesByHour);
    }

    private BigDecimal getNumberOfMinutes() {
        return BigDecimal.valueOf(getDuration().toMinutes());
    }


}
