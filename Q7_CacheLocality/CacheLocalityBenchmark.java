package Q7_CacheLocality;
public class CacheLocalityBenchmark {
    private static final int SIZE = 2000;
    private static final int REPETITIONS = 10;
    private static double resultSink = 0;
    public static void main(String[] args) {
        double[][] matrix = new double[SIZE][SIZE];
        // Initialize the matrix
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = i + j * 0.5;
            }
        }
        // Warm-up runs
        rowMajorSum(matrix);
        columnMajorSum(matrix);
        long rowStart = System.nanoTime();
        for (int r = 0; r < REPETITIONS; r++) {
            resultSink += rowMajorSum(matrix);
        }
        long rowEnd = System.nanoTime();
        long columnStart = System.nanoTime();
        for (int r = 0; r < REPETITIONS; r++) {
            resultSink += columnMajorSum(matrix);
        }
        long columnEnd = System.nanoTime();
        double rowTime =
            (rowEnd - rowStart) / 1_000_000.0;
        double columnTime =
            (columnEnd - columnStart) / 1_000_000.0;
        double speedup = columnTime / rowTime;
        System.out.println("Cache Locality Benchmark");
        System.out.println("Matrix size: " + SIZE + " x " + SIZE);
        System.out.println("Repetitions: " + REPETITIONS);
        System.out.printf("Row-major time: %.3f ms%n", rowTime);
        System.out.printf("Column-major time: %.3f ms%n", columnTime);
        System.out.printf("Column/row time ratio: %.2f%n", speedup);
        System.out.println("Result check: " + resultSink);
    }
    private static double rowMajorSum(double[][] matrix) {
        double sum = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }
    private static double columnMajorSum(double[][] matrix) {
        double sum = 0;
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }
}
