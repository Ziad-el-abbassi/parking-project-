package parking.refactored;

public class Main {
    public static void main(String[] args) {
        Logger logger = new FileLogger("parking.log");
        Formatter formatter = new Formatter();
        ParkingService service = new ParkingService(logger, formatter);

        Vehicle car   = new Vehicle("12345-A-6", "CAR", new CarTariff());
        Vehicle bike  = new Vehicle("67890-B-1", "MOTORBIKE", new MotorbikeTariff());
        Vehicle truck = new Vehicle("24680-D-8", "TRUCK", new TruckTariff());
        CardPayment card = new CardPayment("4242");
        CashPayment cash = new CashPayment();
        service.checkout(car, 3, card);
        service.checkout(bike, 2, cash);
        service.checkout(truck, 4, card);
        System.out.println("Refund " + bike.fee(2) + " MAD at the desk for " + bike.getPlate());
        service.cancel(truck, 4, card);
        System.out.println(service.dailyReport());
    }
}