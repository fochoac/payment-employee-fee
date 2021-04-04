package ec.payment.employee.main;

import ec.payment.employee.exception.GenericException;
import ec.payment.employee.service.PaymentFeeService;
import ec.payment.employee.service.PaymentFeeServiceI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new GenericException("Enter a .txt file with at least five sets of data. For instance: ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00 \n" +
                    "ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00 \n" +
                    "ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00 \n" +
                    "ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00 \n" +
                    "ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00 \n");
        }

        Path textFile = Paths.get(args[0]);
        List<String> employeeList = Files.readAllLines(textFile)
                .stream()
                .filter(emp -> !emp.trim().isEmpty())
                .collect(Collectors.toList());
        validateInputData(employeeList);
        PaymentFeeServiceI paymentFeeService = new PaymentFeeService();
        for (String employee : employeeList) {
            final String paymentFeesInformation = paymentFeeService.printPaymentFee(employee.trim());
            LOGGER.info("\n INPUT \n " + employee + "\n OUTPUT \n " + paymentFeesInformation);

        }

    }

    private static void validateInputData(List<String> employeeList) {
        if (Objects.isNull(employeeList) || employeeList.isEmpty()) {
            throw new GenericException("The file is empty. Check de data before to continue");
        }
        if (employeeList.size() < 5) {
            throw new GenericException("The file should be a .txt file with at least five sets of data");
        }
    }


}
