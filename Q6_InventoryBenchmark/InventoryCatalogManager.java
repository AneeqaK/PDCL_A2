package Q6_InventoryBenchmark;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
public class InventoryCatalogManager {
    private static final int THREADS = 10;
    private static final int OPERATIONS = 50000;
    private static final int ITEMS = 1000;
    public static void main(String[] args)
            throws InterruptedException {
        Map<String, Integer> synchronizedMap =
            Collections.synchronizedMap(new HashMap<>());
        Map<String, Integer> concurrentMap =
            new ConcurrentHashMap<>();
        for (int i = 0; i < ITEMS; i++) {
            String item = "Item-" + i;
            synchronizedMap.put(item, 100);
            concurrentMap.put(item, 100);
        }
        System.out.println("Inventory Catalog Benchmark");
        System.out.println("Worker threads: " + THREADS);
        System.out.println("Total operations: " + OPERATIONS);
        runBenchmark("Synchronized HashMap", synchronizedMap);
        runBenchmark("ConcurrentHashMap", concurrentMap);
    }
    private static void runBenchmark(
            String name, Map<String, Integer> inventory)
            throws InterruptedException {
        Thread[] workers = new Thread[THREADS];
        CountDownLatch ready = new CountDownLatch(THREADS);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(THREADS);
        int operationsPerThread = OPERATIONS / THREADS;
        for (int t = 0; t < THREADS; t++) {
            final int workerId = t;
            workers[t] = new Thread(() -> {
                ready.countDown();
                try {
                    start.await();
                    for (int i = 0; i < operationsPerThread; i++) {
                        int itemNumber =
                            (i + workerId * 17) % ITEMS;
                        String item = "Item-" + itemNumber;
                        if (i % 10 == 0) {
                            inventory.compute(item,
                                (key, stock) ->
                                    stock == null ? 1 : stock + 1);
                        } else {
                            inventory.get(item);
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                }
            }, "Inventory-Worker-" + (t + 1));
            workers[t].start();
        }
        ready.await();
        long startTime = System.nanoTime();
        start.countDown();
        done.await();
        long endTime = System.nanoTime();
        double seconds = (endTime - startTime) / 1_000_000_000.0;
        double throughput = OPERATIONS / seconds;
        System.out.println("\n" + name);
        System.out.printf("Execution time: %.3f ms%n",
            (endTime - startTime) / 1_000_000.0);
        System.out.printf("Throughput: %.0f operations/second%n",
            throughput);
        System.out.println("Sample stock Item-0: "
            + inventory.get("Item-0"));
    }
}
