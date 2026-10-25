package Q5_SensorSearch;
public class ParallelMaxSearch extends SensorSearchEngine {
    private double maximum;
    public ParallelMaxSearch(
            double[] sensorData, int startIdx, int endIdx) {
        super(sensorData, startIdx, endIdx);
        maximum = Double.NEGATIVE_INFINITY;
    }
    @Override
    public void processGrid() {
        for (int i = startIdx; i < endIdx; i++) {
            if (sensorData[i] > maximum) {
                maximum = sensorData[i];
            }
        }
    }
    public double getMaximum() {
        return maximum;
    }
}
