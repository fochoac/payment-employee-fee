package ec.payment.employee.workday;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class PaymentFeesTest {
    private PaymentFees paymentFees;

    @Before
    public void setup() {

    }

    @Test
    public void calculateNormalWorkDayPayment() {
        LocalTime startHour = LocalTime.parse("09:01");
        LocalTime endHour = LocalTime.parse("10:01");
        Duration duration = Duration.between(startHour, endHour);
        paymentFees = new NormalWorkDayPayment(duration, DayOfWeek.MONDAY);
        BigDecimal payment = paymentFees.calculatePayment();
        Assert.assertEquals(BigDecimal.valueOf(15.00).setScale(2), payment);
    }

    @Test
    public void calculateSuplementaryWorkDayPayment() {
        LocalTime startHour = LocalTime.parse("18:01");
        LocalTime endHour = LocalTime.parse("19:01");
        Duration duration = Duration.between(startHour, endHour);
        paymentFees = new SuplementaryWorkDayPayment(duration, DayOfWeek.MONDAY);
        BigDecimal payment = paymentFees.calculatePayment();
        Assert.assertEquals(BigDecimal.valueOf(20.00).setScale(2), payment);
    }

    @Test
    public void calculateExtraordinaryWorkDayPayment() {
        LocalTime startHour = LocalTime.parse("00:01");
        LocalTime endHour = LocalTime.parse("01:01");
        Duration duration = Duration.between(startHour, endHour);
        paymentFees = new ExtraordinaryWorkDayPayment(duration, DayOfWeek.MONDAY);
        BigDecimal payment = paymentFees.calculatePayment();
        Assert.assertEquals(BigDecimal.valueOf(25.00).setScale(2), payment);
    }

    @Test
    public void calculateNormalWorkDayPaymentInWeekend() {
        LocalTime startHour = LocalTime.parse("09:01");
        LocalTime endHour = LocalTime.parse("10:01");
        Duration duration = Duration.between(startHour, endHour);
        paymentFees = new NormalWorkDayPayment(duration, DayOfWeek.SUNDAY);
        BigDecimal payment = paymentFees.calculatePayment();
        Assert.assertEquals(BigDecimal.valueOf(20.00).setScale(2), payment);
    }

    @Test
    public void calculateSuplementaryWorkDayPaymentInWeekend() {
        LocalTime startHour = LocalTime.parse("18:01");
        LocalTime endHour = LocalTime.parse("19:01");
        Duration duration = Duration.between(startHour, endHour);
        paymentFees = new SuplementaryWorkDayPayment(duration, DayOfWeek.SATURDAY);
        BigDecimal payment = paymentFees.calculatePayment();
        Assert.assertEquals(BigDecimal.valueOf(25.00).setScale(2), payment);
    }

    @Test
    public void calculateExtraordinaryWorkDayPaymentInWeekend() {
        LocalTime startHour = LocalTime.parse("00:01");
        LocalTime endHour = LocalTime.parse("01:01");
        Duration duration = Duration.between(startHour, endHour);
        paymentFees = new ExtraordinaryWorkDayPayment(duration, DayOfWeek.SATURDAY);
        BigDecimal payment = paymentFees.calculatePayment();
        Assert.assertEquals(BigDecimal.valueOf(30.00).setScale(2), payment);
    }


}
