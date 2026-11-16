package news;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public final class ThreadsVirtuels {
    private ThreadsVirtuels() {}

    public static void executer() throws Exception {
        System.out.println("\n3. Threads virtuels : 6 tâches attendent en parallèle");
        Instant debut = Instant.now();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<String>> resultats = IntStream.rangeClosed(1, 6)
                    .mapToObj(numero -> executor.submit(() -> {
                        Thread thread = Thread.currentThread();
                        Thread.sleep(500); // Simule une attente réseau ou disque.
                        return "  tâche " + numero + " terminée ; thread virtuel = " + thread.isVirtual();
                    }))
                    .toList();

            for (Future<String> resultat : resultats) {
                System.out.println(resultat.get());
            }
        }

        System.out.println("Durée totale : " + Duration.between(debut, Instant.now()).toMillis()
                + " ms (environ 500 ms car les attentes se chevauchent)");
    }
}
