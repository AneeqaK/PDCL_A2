package Q4_ForkJoinPortfolio;
import java.util.concurrent.RecursiveAction;
public class PortfolioThresholdTask extends RecursiveAction {
    private static final int THRESHOLD = 1000;
    private double[] portfolio;
    private int start;
    private int end;
    private double riskCap;
    public PortfolioThresholdTask(
            double[] portfolio, int start, int end, double riskCap) {
        this.portfolio = portfolio;
        this.start = start;
        this.end = end;
        this.riskCap = riskCap;
    }
    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                if (portfolio[i] > riskCap) {
                    portfolio[i] = portfolio[i] * 0.95;
                }
            }
            return;
        }
        int middle = (start + end) / 2;
        PortfolioThresholdTask left =
            new PortfolioThresholdTask(
                portfolio, start, middle, riskCap);
        PortfolioThresholdTask right =
            new PortfolioThresholdTask(
                portfolio, middle, end, riskCap);
        invokeAll(left, right);
    }
}
