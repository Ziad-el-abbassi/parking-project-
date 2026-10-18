package parking;
import java.io.IOException;
public class Test {
    public static void main(String[] args) throws IOException {
        ParkingGarage garage = new ParkingGarage();
        Vehicle truck = new Vehicle("24680-D-8", "TRUCK");
        double fee = garage.fee(truck, 4);
        PaymentMethod paym=new CardPayment("4242");
        System.out.println("Calculated fee: " + fee);
        garage.checkout(truck, 4, paym);
    }
}
