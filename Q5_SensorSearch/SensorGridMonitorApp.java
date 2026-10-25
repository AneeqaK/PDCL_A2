package Q5_SensorSearch;
public class SensorGridMonitorApp {
    private static final int DATA_SIZE = 50000;
    private static final int WORKERS = 4;
    public static void main(String[] args)
            throws InterruptedException {
        double[] sensorData = new double[DATA_SIZE];
        for (int i = 0; i < DATA_SIZE; i++) {
            sensorData[i] = (i * 37 % 10000) / 10.0;
        }
        int chunkSize = DATA_SIZE / WORKERS;
        ParallelMinSearch[] minTasks =
            new ParallelMinSearch[WORKERS];
        Thread[] minThreads = new Thread[WORKERS];
        long minStart = System.nanoTime();
        for (int i = 0; i < WORKERS; i++) {
            int start = i * chunkSize;
            int end = (i == WORKERS - 1)
                    ? DATA_SIZE : start + chunkSize;
            minTasks[i] = new ParallelMinSearch(
                sensorData, start, end);
            minThreads[i] = new Thread(
                minTasks[i], "Min-Worker-" + (i + 1));
            minThreads[i].start();
        }
        for (Thread thread : minThreads) {
            thread.join();
        }
        double overallMin = Double.POSITIVE_INFINITY;
        for (ParallelMinSearch task : minTasks) {
            if (task.getMinimum() < overallMin) {
                overallMin = task.getMinimum();
            }
        }
        long minEnd = System.nanoTime();
        ParallelMaxSearch[] maxTasks =
            new ParallelMaxSearch[WORKERS];
        Thread[] maxThreads = new Thread[WORKERS];
        long maxStart = System.nanoTime();
        for (int i = 0; i < WORKERS; i++) {
            int start = i * chunkSize;
            int end = (i == WORKERS - 1)
                    ? DATA_SIZE : start + chunkSize;
            maxTasks[i] = new ParallelMaxSearch(
                sensorData, start, end);
            maxThreads[i] = new Thread(
                maxTasks[i], "Max-Worker-" + (i + 1));
            maxThreads[i].start();
        }
        for (Thread thread : maxThreads) {
            thread.join();
        }
        double overallMax = Double.NEGATIVE_INFINITY;
        for (ParallelMaxSearch task : maxTasks) {
            if (task.getMaximum() > overallMax) {
                overallMax = task.getMaximum();
            }
        }
        long maxEnd = System.nanoTime();
        System.out.println("Sensor Grid Monitoring");
        System.out.println("Sensor readings: " + DATA_SIZE);
        System.out.println("Worker threads per search: " + WORKERS);
        System.out.println("Minimum reading: " + overallMin);
        System.out.println("Maximum reading: " + overallMax);
        System.out.printf(
            "Parallel minimum search: %.3f ms%n",
            (minEnd - minStart) / 1_000_000.0);
        System.out.printf(
            "Parallel maximum search: %.3f ms%n",
            (maxEnd - maxStart) / 1_000_000.0);
    }
}

