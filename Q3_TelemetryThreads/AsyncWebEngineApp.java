package Q3_TelemetryThreads;
public class AsyncWebEngineApp {
    public static void main(String[] args) {
        Thread logWorker = new Thread(
            new LogProcessorTask(), "Log-Worker"
        );
        TelemetryThread telemetryWorker =
            new TelemetryThread("Telemetry-Worker");
        System.out.println(
            "Main thread: " + Thread.currentThread().getName()
        );
        logWorker.start();
        telemetryWorker.start();
        System.out.println(
            "Log worker state: " + logWorker.getState()
        );
        System.out.println(
            "Telemetry worker state: "
            + telemetryWorker.getState()
        );
        try {
            logWorker.join();
            telemetryWorker.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread interrupted.");
        }
        System.out.println("All workers completed.");
    }
}

