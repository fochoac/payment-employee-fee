package ec.payment.employee.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaymentFeeServiceTest {
    private PaymentFeeService paymentFeeService;

    @Before
    public void setup() {
        this.paymentFeeService = new PaymentFeeService();
    }

    @Test
    public void printPaymentFee() {
        List<String> testResult = new ArrayList<>();
        for (String employeeWorkDay : generateInputTest()) {
            testResult.add(paymentFeeService.printPaymentFee(employeeWorkDay));
        }
        final String[] expectedResultArray = new String[]{
                "The amount to pay RENE is: 215 USD",
                "The amount to pay ASTRID is: 85 USD"};
        Assert.assertArrayEquals(expectedResultArray, testResult.toArray(new String[]{}));
    }

    @Test
    public void calculateTotalToPay() {

        final String inputData = "MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00";
        BigDecimal valueToPay = paymentFeeService.calculateTotalToPay(inputData);
        Assert.assertEquals(BigDecimal.valueOf(215).setScale(2), valueToPay);
    }

    @Test
    public void calculateTotalToPayMindNight() {

        final String inputData = "SU20:00-00:00";
        BigDecimal valueToPay = paymentFeeService.calculateTotalToPay(inputData);
        Assert.assertEquals(BigDecimal.valueOf(80).setScale(2), valueToPay);


    }

    @Test
    public void calculateTotalToPayMindNightPlusOneMinute() {

        final String inputData = "SU00:01-04:00";
        BigDecimal valueToPay = paymentFeeService.calculateTotalToPay(inputData);
        Assert.assertEquals(BigDecimal.valueOf(119.50).setScale(2), valueToPay);


    }

    @Test(expected = UnsupportedOperationException.class)
    public void calculateTotalToPayStarTimeIqualToMindNight() {

        final String inputData = "SU00:00-04:00";
        paymentFeeService.calculateTotalToPay(inputData);


    }

    private List<String> generateInputTest() {
        return Arrays.asList(
                "RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00",
                "ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00"
        );
    }
}
