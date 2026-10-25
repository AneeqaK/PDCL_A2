package Q5_SensorSearch;
public class ParallelMinSearch extends SensorSearchEngine {
    private double minimum;
    public ParallelMinSearch(
            double[] sensorData, int startIdx, int endIdx) {
        super(sensorData, startIdx, endIdx);
        minimum = Double.POSITIVE_INFINITY;
    }
    @Override
    public void processGrid() {
        for (int i = startIdx; i < endIdx; i++) {
            if (sensorData[i] < minimum) {
                minimum = sensorData[i];
            }
        }
    }
    public double getMinimum() {
        return minimum;
    }
}
