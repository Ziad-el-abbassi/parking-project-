package parking.refactored;
import java.util.ArrayList;
import java.util.List;

public class ParkingServiceTest {
    static class Logged implements Logger {
        final List<String> lines = new ArrayList<>();
        public void log(String line) {
            lines.add(line);
        }
    }
    public static void main(String[] args) {
        Logged log = new Logged();
        ParkingService service = new ParkingService(log, new Formatter());
        Vehicle car   = new Vehicle("12345-A-6", "CAR", new CarTariff());
        Vehicle bike  = new Vehicle("67890-B-1", "MOTORBIKE", new MotorbikeTariff());
        Vehicle truck = new Vehicle("24680-D-8", "TRUCK", new TruckTariff());

        System.out.println("car 3 h: " + (car.fee(3) == 15.0 ? "PASS" : "FAIL"));
        System.out.println("bike 2 h: " + (bike.fee(2) == 4.0 ? "PASS" : "FAIL"));
        System.out.println("truck 4 h: " + (truck.fee(4) == 60.0 ? "PASS" : "FAIL"));
        service.checkout(car, 3, new CardPayment("4242"));
        service.checkout(bike, 2, new CashPayment());
        service.checkout(truck, 4, new CardPayment("4242"));
        String report = service.dailyReport();
        System.out.println("report total: " + (report.contains("Total: 79.0 MAD") ? "PASS" : "FAIL"));
        System.out.println("3 receipts logged: " + (log.lines.size() == 3 ? "PASS" : "FAIL"));
    }
}