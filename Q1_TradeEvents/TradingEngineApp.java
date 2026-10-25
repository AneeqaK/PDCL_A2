package Q1_TradeEvents;
public class TradingEngineApp{
    public static void main(String[] args){
        TradeEvent event1 = new FIXOrderEvent(
            "FIX-Gateway", System.currentTimeMillis(),
            100.0, "ORD101", 10
        );
        TradeEvent event2 = new ReutersQuoteEvent(
            "Reuters-Gateway", System.currentTimeMillis(),
            101.0, 99.50, 100.50
        );
        event1.dispatch();
        event2.dispatch();
    }
}
