package Q4_ForkJoinPortfolio;
import java.util.concurrent.RecursiveTask;
public class ParallelRiskCalculator extends RecursiveTask<Double> {
    private static final int THRESHOLD = 1000;
    private double[] riskData;
    private int start;
    private int end;
    public ParallelRiskCalculator(double[] riskData, int start, int end) {
        this.riskData = riskData;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Double compute() {
        if (end - start <= THRESHOLD) {
            double total = 0;
            for (int i = start; i < end; i++) {
                total += riskData[i];
            }
            return total;
        }
        int middle = (start + end) / 2;
        ParallelRiskCalculator left =
            new ParallelRiskCalculator(riskData, start, middle);
        ParallelRiskCalculator right =
            new ParallelRiskCalculator(riskData, middle, end);
        left.fork();
        double rightResult = right.compute();
        double leftResult = left.join();
        return leftResult + rightResult;
    }
}
