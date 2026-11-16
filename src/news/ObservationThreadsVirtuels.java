package news;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public final class ObservationThreadsVirtuels {
    private ObservationThreadsVirtuels() {}

    public static void executer() throws Exception {
        long pid = ProcessHandle.current().pid();
        Path jcmd = Path.of(System.getProperty("java.home"), "bin", "jcmd.exe");
        System.out.println("Mode observation : 4 threads virtuels attendent pendant 45 secondes.");
        System.out.println("Dans un autre terminal PowerShell : Get-Process -Id " + pid);
        System.out.println("Pour une vue des threads : & '" + jcmd + "' " + pid
                + " Thread.dump_to_file -format=json threads.json");

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<Object>> taches = IntStream.rangeClosed(1, 4)
                    .mapToObj(numero -> executor.submit(() -> {
                        System.out.println("  tâche " + numero + " démarrée sur un thread virtuel");
                        Thread.sleep(45_000);
                        System.out.println("  tâche " + numero + " terminée");
                        return null;
                    }))
                    .toList();

            for (Future<Object> tache : taches) {
                tache.get();
            }
        }
    }
}
