package ec.payment.employee.main;

import ec.payment.employee.exception.GenericException;
import ec.payment.employee.service.PaymentFeeService;
import ec.payment.employee.service.PaymentFeeServiceI;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new GenericException("Enter the requiered parameters. For instance: ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00");
        }
        PaymentFeeServiceI paymentFeeService = new PaymentFeeService();
        final String paymentFeesInformation = paymentFeeService.printPaymentFee(args[0]);
        System.out.println(paymentFeesInformation);
    }
}
