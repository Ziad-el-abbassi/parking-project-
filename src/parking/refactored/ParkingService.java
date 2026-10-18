package parking.refactored;
import java.util.ArrayList;
import java.util.List;

public class ParkingService {
    private final Logger logger;
    private final Formatter formatter;
    private final List<String> receipts = new ArrayList<>();
    private double total;

    public ParkingService(Logger logger, Formatter formatter) {
        this.logger = logger;
        this.formatter = formatter;
    }
    public void checkout(Vehicle vehicle, int hours, PaymentMethod method) {
        double amount = vehicle.fee(hours);
        method.pay(amount);
        total += amount;
        String receipt = formatter.receipt(vehicle, hours, amount, method);
        receipts.add(receipt);
        logger.log(receipt);
    }
    public void cancel(Vehicle vehicle, int hours, RefundablePayment method) {
        double amount = vehicle.fee(hours);
        method.refund(amount);
        total -= amount;
    }
    public String dailyReport() {
        return formatter.dailyReport(receipts, total);
    }
}