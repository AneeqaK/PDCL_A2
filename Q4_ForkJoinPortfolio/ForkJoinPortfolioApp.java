package Q4_ForkJoinPortfolio;
import java.util.concurrent.ForkJoinPool;
public class ForkJoinPortfolioApp {
    public static void main(String[] args) {
        int size = 10000;
        double[] portfolio = new double[size];
        for (int i = 0; i < size; i++) {
            portfolio[i] = 50 + (i % 100);
        }
        ForkJoinPool pool = new ForkJoinPool();
        long riskStart = System.nanoTime();
        double totalRisk = pool.invoke(
            new ParallelRiskCalculator(portfolio, 0, size)
        );
        long riskEnd = System.nanoTime();
        double riskCap = 120.0;
        long adjustStart = System.nanoTime();
        pool.invoke(
            new PortfolioThresholdTask(
                portfolio, 0, size, riskCap)
        );
        long adjustEnd = System.nanoTime();
        int adjustedCount = 0;
        for (double value : portfolio) {
            if (value < riskCap) {
                adjustedCount++;
            }
        }
        System.out.println("Portfolio Risk Calculation");
        System.out.println("Number of values: " + size);
        System.out.printf("Total risk: %.2f%n", totalRisk);
        System.out.printf(
            "Risk calculation time: %.3f ms%n",
            (riskEnd - riskStart) / 1_000_000.0
        );
        System.out.println("\nPortfolio Threshold Adjustment");
        System.out.println("Risk cap: " + riskCap);
        System.out.println(
            "Values now below risk cap: " + adjustedCount
        );
        System.out.printf(
            "Adjustment time: %.3f ms%n",
            (adjustEnd - adjustStart) / 1_000_000.0
        );
        pool.shutdown();
    }
}
