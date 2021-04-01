package ec.payment.employee.service;

import ec.payment.employee.enumeration.WorkDayPeriodEnum;
import ec.payment.employee.model.DayOfWeekWithTime;
import ec.payment.employee.workday.ExtraordinaryWorkDayPayment;
import ec.payment.employee.workday.NormalWorkDayPayment;
import ec.payment.employee.workday.PaymentFees;
import ec.payment.employee.workday.SuplementaryWorkDayPayment;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PaymentFeeService {
    public static final String EQUAL_SEPARATOR = "=";
    public static final String COMMA_SEPARATOR = ",";
    public static final String AMOUNT_TO_PAY_TO_PRINT = "The amount to pay %s is: %s USD";
    private static final int EMPLOYEE_NAME_PARAMETER = 0;
    private static final int DAYOFWEEK_WITH_TIME_PARAMETER = 1;

    public String printPaymentFee(String dataFrameOfWorkDay) {
        final String[] inputParameters = dataFrameOfWorkDay.split(EQUAL_SEPARATOR);
        final String employeeName = inputParameters[EMPLOYEE_NAME_PARAMETER];
        BigDecimal totalToPay = calculateTotalToPay(inputParameters[DAYOFWEEK_WITH_TIME_PARAMETER]);
        NumberFormat format = DecimalFormat.getInstance(Locale.US);
        format.setCurrency(Currency.getInstance(Locale.US));
        final String textMonetaryValue = format.format(totalToPay.doubleValue());
        return String.format(AMOUNT_TO_PAY_TO_PRINT, employeeName, textMonetaryValue);
    }

    public BigDecimal calculateTotalToPay(String secuenceDayOfWeekWithTime) {
        List<DayOfWeekWithTime> workingDays = Stream.of(secuenceDayOfWeekWithTime.split(COMMA_SEPARATOR))
                .map(DayOfWeekWithTime::new)
                .collect(Collectors.toList());
        BigDecimal totalToPay = BigDecimal.ZERO;
        for (DayOfWeekWithTime dayOfWeekWithTime : workingDays) {
            PaymentFees paymentFees = null;
            if (WorkDayPeriodEnum.isSuplementaryPeriod(dayOfWeekWithTime)) {
                paymentFees = new SuplementaryWorkDayPayment(dayOfWeekWithTime);
            }
            if (WorkDayPeriodEnum.isExtraordinaryPeriod(dayOfWeekWithTime)) {
                paymentFees = new ExtraordinaryWorkDayPayment(dayOfWeekWithTime);
            }
            if (WorkDayPeriodEnum.isNormalPeriod(dayOfWeekWithTime)) {
                paymentFees = new NormalWorkDayPayment(dayOfWeekWithTime);
            }
            if (Objects.isNull(paymentFees)) {
                throw new UnsupportedOperationException("The entry time has inconsistencies. Check the data: " + dayOfWeekWithTime.toString());
            }
            totalToPay = totalToPay.add(paymentFees.calculatePayment());
        }
        return totalToPay;
    }


}
