package Q1_TradeEvents;
public class FIXOrderEvent extends TradeEvent {
    private String clOrdId;
    private int quantity;
    public FIXOrderEvent(String senderId, long timestamp,
                         double basePrice, String clOrdId,
                         int quantity) {
        super(senderId, timestamp, basePrice);
        this.clOrdId = clOrdId;
        this.quantity = quantity;
    }
    @Override
    public void dispatch() {
        double orderValue = basePrice * quantity;
        double fee = orderValue * 0.0015;
        double totalCost = orderValue + fee;
        System.out.println("\nFIX Purchase Order");
        System.out.println("Order ID: " + clOrdId);
        System.out.println("Sender: " + senderId);
        System.out.println("Quantity: " + quantity);
        System.out.printf("Order Value: %.2f%n", orderValue);
        System.out.printf("Transaction Fee: %.2f%n", fee);
        System.out.printf("Total Cost: %.2f%n", totalCost);
    }
}

